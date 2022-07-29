package com.so.dtos;

import com.so.enums.QuestionStatus;
import com.so.models.Members;
import com.so.models.Question;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Getter
@Setter
@ToString
public class SearchDTO extends Question {
    private String title;
    private String description;
    private QuestionStatus questionStatus;

    private Members member;

}
