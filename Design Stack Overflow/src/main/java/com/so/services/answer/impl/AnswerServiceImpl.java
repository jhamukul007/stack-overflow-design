package com.so.services.answer.impl;

import com.so.dtos.AnswerDto;
import com.so.enums.AnswerStatus;
import com.so.enums.QuestionStatus;
import com.so.exceptions.NotFoundException;
import com.so.exceptions.OperationNotAllowedException;
import com.so.models.Answer;
import com.so.models.Members;
import com.so.models.Question;
import com.so.repo.question.AnswerRepo;
import com.so.services.answer.AnswerService;
import com.so.services.member.MemberService;
import com.so.services.question.QuestionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepo answerDto;
    private final ModelMapper modelMapper;
    private final MemberService memberService;
    private final QuestionService questionService;

    @Override
    public Answer saveOrUpdate(Answer value) {
        return answerDto.save(value);
    }

    @Override
    public Answer saveOrUpdateTx(Answer value) {
        return answerDto.save(value);
    }

    @Override
    public Optional<Answer> findById(@NonNull Long id) {
        return answerDto.findById(id);
    }

    @Override
    public Optional<AnswerDto> createAnswer(AnswerDto answerDto) {
        Optional<Members> members = memberService.findById(answerDto.getMemberId());
        if(members.isEmpty())
            throw new OperationNotAllowedException("You are not allowed to answer question");
        Optional<Question> question =  questionService.findById(answerDto.getQuestionId());
        if(question.isEmpty())
            throw new NotFoundException("question does not exist or removed due to policy violation");
        if(QuestionStatus.CLOSED.equals(question.get().getQuestionStatus()))
            throw new OperationNotAllowedException("Question marked closed");

        Answer answer = modelMapper.map(answerDto, Answer.class);
        answer.setAnswerStatus(AnswerStatus.ACTIVE);
        saveOrUpdateTx(answer);
        answer.setMembers(members.get());
        saveOrUpdateTx(answer);
        return Optional.of(modelMapper.map(answer, AnswerDto.class));
    }

    @Override
    public Optional<AnswerDto> editAnswer(AnswerDto answerDto) {
        Answer answer = modelMapper.map(answerDto, Answer.class);
        saveOrUpdateTx(answer);
        return Optional.of(modelMapper.map(answer, AnswerDto.class));
    }

    @Override
    public List<AnswerDto> getAnswersByQuestionId(Long answerId, int pageSize, int page) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("modifiedDate")));
        return answerDto.findByQuestionId(answerId, pageRequest).stream().map(answer ->
                modelMapper.map(answer, AnswerDto.class)).collect(Collectors.toList());
    }

}
