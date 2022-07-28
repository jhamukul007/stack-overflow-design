package com.so.repo.question;

import com.so.models.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepo extends JpaRepository<Tags, Long> {
    Optional<Tags> findByTitle(String title);
}
