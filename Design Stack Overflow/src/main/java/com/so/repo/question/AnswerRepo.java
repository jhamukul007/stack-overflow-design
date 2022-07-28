package com.so.repo.question;

import com.so.models.Answer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {
    Optional<List<Answer>> findByQuestionId(Long questionId, PageRequest pageRequest);
}
