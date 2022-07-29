package com.so.models;

import com.so.enums.AnswerStatus;
import lombok.AllArgsConstructor;
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

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseEntity{

    private String description;

    @OneToOne(targetEntity = Members.class, cascade = CascadeType.ALL)
    private Members members;

    @OneToOne(targetEntity = Question.class, cascade = CascadeType.ALL)
    private Question question;

    @Enumerated(EnumType.STRING)
    @Column(name = "answer_status")
    private AnswerStatus answerStatus;
}
