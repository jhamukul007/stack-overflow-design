package com.so.repo.question;

import com.so.models.Question;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    List<Question> findByMemberId(Long memberId, PageRequest pageRequest);
    List<Question> findByMemberId(Long memberId);
    List<Question> findByTitleContains(String title, PageRequest pageRequest);
}
