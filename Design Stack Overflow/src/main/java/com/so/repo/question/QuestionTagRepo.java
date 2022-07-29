package com.so.repo.question;

import com.so.models.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionTagRepo extends JpaRepository<QuestionTag, Long> {
    @Query("select qt from QuestionTag qt where qt.questionId.id in :questionIds")
    List<QuestionTag> findByQuestionIdIn(@Param("questionIds") List<Long> questionIds);

    @Query("select qt from QuestionTag qt where qt.questionId.id = :questionId")
    List<QuestionTag> findByQuestionId(@Param("questionId") Long questionId);
}
