package com.so.services.comment;

import com.so.dtos.CommentDto;
import com.so.enums.EntityType;
import com.so.models.Comment;
import com.so.services.BaseService;

import java.util.List;
import java.util.Optional;

public interface CommentService extends BaseService<Comment> {
    Optional<Comment> createComment(CommentDto commentDto);
    List<Comment> getCommentByEntityIdAndEntityType(Long entityId, EntityType entityType);
    Iterable<Comment> getCommentsByEntityIds(Iterable<Long> entityId);
}
