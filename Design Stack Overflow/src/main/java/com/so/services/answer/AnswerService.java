package com.so.services.answer;

import com.so.dtos.AnswerDto;
import com.so.models.Answer;
import com.so.services.BaseService;

import java.util.List;
import java.util.Optional;

public interface AnswerService extends BaseService<Answer> {
    Optional<AnswerDto> createAnswer(AnswerDto answerDto);
    Optional<AnswerDto> editAnswer(AnswerDto answerDto);
    List<AnswerDto> getAnswersByQuestionId(Long answerId, int pageSize, int page);
}
