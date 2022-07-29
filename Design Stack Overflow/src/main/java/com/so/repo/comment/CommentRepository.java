package com.so.repo.comment;

import com.so.enums.EntityType;
import com.so.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByEntityIdAndEntityType(Long entityId, EntityType entityType);
}
