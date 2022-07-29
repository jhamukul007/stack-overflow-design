package com.so.services.comment.impl;

import com.so.dtos.CommentDto;
import com.so.enums.EntityType;
import com.so.exceptions.NotFoundException;
import com.so.exceptions.OperationNotAllowedException;
import com.so.models.Answer;
import com.so.models.Comment;
import com.so.models.Members;
import com.so.models.Question;
import com.so.repo.comment.CommentRepository;
import com.so.services.answer.AnswerService;
import com.so.services.comment.CommentService;
import com.so.services.member.MemberService;
import com.so.services.question.QuestionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, MemberService memberService,
                              QuestionService questionService, AnswerService answerService) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.memberService = memberService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @Override
    public Comment saveOrUpdate(Comment value) {
        return commentRepository.save(value);
    }

    @Override
    public Comment saveOrUpdateTx(Comment value) {
        return commentRepository.save(value);
    }

    @Override
    public Optional<Comment> findById(@NonNull Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public <S extends Comment> List<S> saveAll(List<S> entities) {
        return commentRepository.saveAll(entities);
    }

    @Override
    public Optional<Comment> createComment(CommentDto commentDto) {
        Optional<Members> members = memberService.findById(commentDto.getMemberId());
        if (members.isEmpty())
            throw new OperationNotAllowedException("Only member can give comment !! create account first");
        validateComment(commentDto);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setMembers(members.get());
        return Optional.of(saveOrUpdateTx(comment));
    }

    @Override
    public List<Comment> getCommentByEntityIdAndEntityType(Long entityId, EntityType entityType) {
        return commentRepository.findByEntityIdAndEntityType(entityId, entityType);
    }

    @Override
    public Iterable<Comment> getCommentsByEntityIds(Iterable<Long> entityId) {
        return commentRepository.findAllById(entityId);
    }

    private void validateComment(CommentDto commentDto) {
        if (EntityType.QUESTION.equals(commentDto.getEntityType())) {
            Optional<Question> questionOp = questionService.findById(commentDto.getEntityId());
            if (questionOp.isEmpty())
                throw new NotFoundException("question not found");
        }

        if (EntityType.ANSWER.equals(commentDto.getEntityType())) {
            Optional<Answer> questionOp = answerService.findById(commentDto.getEntityId());
            if (questionOp.isEmpty())
                throw new NotFoundException("answer not found");
        }
    }
}
