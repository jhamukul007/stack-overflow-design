package com.so.dtos;

import com.so.models.Tags;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuestionDto {
    private Long id;
    private String title;
    private String description;
    private Long memberId;
    private Date createdDate;
    private Date modifiedDate;
    private List<Tags> tagIds = new ArrayList<>();

    public void addTags(Tags tag){
        this.getTagIds().add(tag);
    }


}
