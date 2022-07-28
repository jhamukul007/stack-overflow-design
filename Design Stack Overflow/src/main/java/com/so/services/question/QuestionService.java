package com.so.services.question;

import com.so.dtos.QuestionDto;
import com.so.models.Question;
import com.so.services.BaseService;
import com.so.vos.QuestionVO;

import java.util.List;
import java.util.Optional;

public interface QuestionService extends BaseService<Question> {
    Optional<QuestionDto> createQuestion(QuestionVO questionVO);
    Optional<QuestionDto> editQuestion(QuestionVO questionVO);
    List<QuestionDto> findByMemberId(Long memberId, int size, int page);
    List<QuestionDto> findByMemberId(Long memberId);
    List<Question> findAll();
    List<Question> searchQuestion(String title, int page, int size);
}
