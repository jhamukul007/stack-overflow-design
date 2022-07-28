package com.so.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table
@NoArgsConstructor
public class QuestionTag extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Tags.class)
    private Tags tagId;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Question.class)
    private Question questionId;
}
