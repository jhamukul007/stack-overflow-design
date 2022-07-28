package com.so.controllers;

import com.so.models.Question;
import com.so.models.QuestionTag;
import com.so.services.tags.QuestionTagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question_tag")
@AllArgsConstructor
public class QuestionTagController {
    private final QuestionTagService questionTagService;

    @GetMapping("/all")
    @ResponseBody
    public List<QuestionTag> getAllQuestions () {
        return questionTagService.findAll();
    }
}
