package com.so.models;

import com.so.enums.CommentStatus;
import com.so.enums.EntityType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.lang.reflect.Member;

@Getter
@Setter
@ToString
@Entity
@Table
@NoArgsConstructor
public class Comment extends BaseEntity{

    private Long entityId;

    @Column(name = "entity_type")
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

    @OneToOne(targetEntity = Members.class, cascade = CascadeType.ALL)
    private Members members;

    private String description;

    @Column(name = "comment_status")
    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;
}
