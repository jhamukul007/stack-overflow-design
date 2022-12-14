package com.so.services.tags;

import com.so.models.Question;
import com.so.models.QuestionTag;
import com.so.services.BaseService;
import lombok.NonNull;

import java.util.List;

public interface QuestionTagService extends BaseService<QuestionTag> {
    List<QuestionTag> saveQuestionTag(Question question, List<Long> tagIds);
    List<QuestionTag> getQuestionTagsByQuestionsId(List<Long> questionIds);
    List<QuestionTag> getQuestionTagsByQuestionsId(@NonNull Long questionId);
    List<QuestionTag> findAll();
}
