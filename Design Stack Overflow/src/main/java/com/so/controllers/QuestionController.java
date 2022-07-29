package com.so.controllers;

import com.so.dtos.QuestionDto;
import com.so.models.Question;
import com.so.services.question.QuestionService;
import com.so.vos.QuestionVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody QuestionVO questionVO) {
        return new ResponseEntity<>(questionService.createQuestion(questionVO).get(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<QuestionDto> editQuestion(@Valid @RequestBody QuestionVO questionVO) {
        return ResponseEntity.of(questionService.editQuestion(questionVO));
    }

    @GetMapping("/member/{memberId}")
    @ResponseBody
    public List<QuestionDto> getQuestionsPostedByMember(@PathVariable Long memberId,
                                                        @RequestParam(value = "page") int page,
                                                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return questionService.findByMemberId(memberId, size, page);
        //return questionService.findByMemberId(memberId);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Question> getAllQuestions () {
        return questionService.findAll();
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Question> searchQuestion(@RequestParam("title") String title, @RequestParam("page") int page,
                                         @RequestParam("size") int size) {
        return questionService.searchQuestion(title, page, size);
        Optional<String> abs= Optional.empty();
        abs.isPresent()
    }

}
