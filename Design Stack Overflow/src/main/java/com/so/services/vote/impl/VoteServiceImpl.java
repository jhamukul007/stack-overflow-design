package com.so.services.vote.impl;

import com.so.enums.EntityType;
import com.so.enums.VoteType;
import com.so.exceptions.NotFoundException;
import com.so.exceptions.OperationNotAllowedException;
import com.so.models.Answer;
import com.so.models.Members;
import com.so.models.Question;
import com.so.models.Vote;
import com.so.repo.vote.VoteRepository;
import com.so.services.answer.AnswerService;
import com.so.services.member.MemberService;
import com.so.services.question.QuestionService;
import com.so.services.vote.VoteService;
import com.so.vos.vote.VoteVo;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ModelMapper modelMapper;

    @Override
    public Vote saveOrUpdate(Vote value) {
        return voteRepository.save(value);
    }

    @Override
    public Vote saveOrUpdateTx(Vote value) {
        return voteRepository.save(value);
    }

    @Override
    public Optional<Vote> findById(@NonNull Long id) {
        return voteRepository.findById(id);
    }

    @Override
    public <S extends Vote> List<S> saveAll(List<S> entities) {
        return voteRepository.saveAll(entities);
    }

    @Override
    public Optional<Vote> createVote(VoteVo vo) {
        Optional<Members> membersOp = memberService.findById(vo.getMemberId());
        if (membersOp.isEmpty())
            throw new OperationNotAllowedException("Only member can add vote");
        Members author = null;
        if (EntityType.QUESTION.equals(vo.getEntityType())) {
            Optional<Question> questionOp = questionService.findById(vo.getEntityId());
            if (questionOp.isEmpty())
                throw new NotFoundException("question not found");
            author = questionOp.get().getMember();
        }

        if (EntityType.ANSWER.equals(vo.getEntityType())) {
            Optional<Answer> answerOp = answerService.findById(vo.getEntityId());
            if (answerOp.isEmpty())
                throw new NotFoundException("answer not found");
            author = answerOp.get().getMembers();
        }

        if (Objects.isNull(vo.getPoints()))
            vo.setPoints(vo.getVoteType().getPoint());

        Vote vote = modelMapper.map(vo, Vote.class);
        vote.setMember(membersOp.get());
        saveOrUpdateTx(vote);
        //Adding points to user
        author.setPoints(author.getPoints() + vo.getPoints());
        memberService.saveOrUpdate(membersOp.get());
        //
        return Optional.of(vote);
    }

    @Override
    public Optional<Long> countVoteByQuestionId(Long questionId, VoteType voteType) {
        return voteRepository.countAllByEntityIdAndEntityTypeAndVoteType(questionId, EntityType.QUESTION, voteType);
    }

}
