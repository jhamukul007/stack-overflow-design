package com.so.dtos;

import com.so.enums.AnswerStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AnswerDto {
    private Long id;
    @NotEmpty(message = "answer description must not be null")
    private String description;
    @NotNull(message = "memberId must not be null")
    private Long memberId;
    @NotNull(message = "memberId must not be null")
    private Long questionId;
    private AnswerStatus answerStatus;
    private Date createdDate;
    private Date modifiedDate;
}
