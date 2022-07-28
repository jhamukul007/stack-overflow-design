package com.so.vos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionVO {
    private Long id;

    @NotEmpty(message = "question title must not be null")
    private String title;

    @NotEmpty(message = "question description must not be null")
    private String description;

    @NotNull(message = "who is posted the question ?")
    private Long memberId;

    private List<Long> tagIds;
}
