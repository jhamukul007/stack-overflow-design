package com.so.services.question.impl;

import com.so.dtos.QuestionDto;
import com.so.enums.QuestionStatus;
import com.so.exceptions.OperationNotAllowedException;
import com.so.models.Members;
import com.so.models.Question;
import com.so.models.QuestionTag;
import com.so.repo.question.QuestionRepo;
import com.so.services.member.MemberService;
import com.so.services.question.QuestionService;
import com.so.services.tags.QuestionTagService;
import com.so.vos.QuestionVO;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;
    private final QuestionTagService questionTagService;
    private final ModelMapper modelmapper;
    private final MemberService memberService;

    @Override
    public Optional<QuestionDto> createQuestion(QuestionVO questionVO) {
        Optional<Members> member = memberService.findById(questionVO.getMemberId());
        if(member.isEmpty())
            throw new OperationNotAllowedException("Only member can ask question !! create account first");

        Question question = modelmapper.map(questionVO, Question.class);
        question.setQuestionStatus(QuestionStatus.OPEN);
        question.setMember(member.get());
        saveOrUpdateTx(question);
        List<QuestionTag> qTags = questionTagService.saveQuestionTag(question, questionVO.getTagIds());
        QuestionDto qDto = modelmapper.map(question, QuestionDto.class);
        qDto.setTagIds(qTags.stream().map(qTag -> qTag.getTagId()).collect(Collectors.toList()));
        return Optional.of(qDto);
    }

    @Override
    public Optional<QuestionDto> editQuestion(QuestionVO questionVO) {
        Question question = modelmapper.map(questionVO, Question.class);
        saveOrUpdateTx(question);
        return Optional.of(modelmapper.map(question, QuestionDto.class));
    }

    @Override
    public List<QuestionDto> findByMemberId(Long memberId) {

        return questionRepo.findByMemberId(memberId).stream().map(question ->
                modelmapper.map(question, QuestionDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> findByMemberId(Long memberId, int size, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Order.asc("modifiedDate")));
        List<Question> questions = questionRepo.findByMemberId(memberId, pageRequest);

        List<QuestionDto> dtos = questions.stream().map(question ->
                modelmapper.map(question, QuestionDto.class)).collect(Collectors.toList());
        Map<Long, QuestionDto> questionMapVsId = dtos.stream().collect(Collectors.toMap(QuestionDto::getId, Function.identity()));
        List<Long> questionIds = dtos.stream().map(QuestionDto::getId).collect(Collectors.toList());
        List<QuestionTag> questionTags = questionTagService.getQuestionTagsByQuestionsId(questionIds);
        questionTags.forEach(questionTag ->
            questionMapVsId.get(questionTag.getQuestionId().getId()).addTags(questionTag.getTagId()) );
        return dtos;
    }


    @Override
    public List<Question> findAll() {
        return questionRepo.findAll();
    }

    @Override
    public List<Question> searchQuestion(String title, int page, int size) {
        PageRequest request = PageRequest.of(page-1, size);
        return questionRepo.findByTitleContains(title, request);
    }

    @Override
    public Question saveOrUpdateTx(Question value) {
        return questionRepo.save(value);
    }

    @Override
    public Optional<Question> findById(@NonNull Long id) {
        return questionRepo.findById(id);
    }

    @Override
    public <S extends Question> List<S> saveAll(List<S> entities) {
        return questionRepo.saveAll(entities);
    }
}
