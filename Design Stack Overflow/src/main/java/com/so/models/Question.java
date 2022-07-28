package com.so.models;

import com.so.enums.QuestionStatus;
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
public class Question extends BaseEntity {

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Members.class)
    private Members member;
}
