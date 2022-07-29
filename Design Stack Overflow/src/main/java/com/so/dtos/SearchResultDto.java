package com.so.dtos;

import com.so.enums.AnswerStatus;
import com.so.models.Answer;
import com.so.models.Comment;
import com.so.models.Members;
import com.so.models.Question;
import com.so.models.Tags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class SearchResultDto {
    private Question question;
    private List<Tags> questionTags;
    private List<Comment> questionComment;
    private Long upVoteCount;
    private AnswerDetailsDto answers;
}

