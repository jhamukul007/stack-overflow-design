package com.so.dtos;

import com.so.enums.CommentStatus;
import com.so.enums.EntityType;
import com.so.models.Members;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDto {

    @NotNull(message = "entityId must not be null")
    private Long entityId;

    @NotNull(message = "entityType must not be null")
    private EntityType entityType;

    @NotNull(message = "memberId must not be null")
    private Long memberId;

    @NotEmpty(message = "description must not be null")
    private String description;

}
