package com.so.dtos;

import com.so.enums.AnswerStatus;
import com.so.models.Comment;
import com.so.models.Members;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AnswerDetails {
    private Long id;
    private Date createdDate;
    private Date modifiedDate;
    private String description;
    private Members members;
    private AnswerStatus answerStatus;
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        this.comments.add(comment);
    }
}
