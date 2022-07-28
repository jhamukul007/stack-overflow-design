package com.so.controllers;

import com.so.dtos.AnswerDto;
import com.so.services.answer.AnswerService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/answer")
@AllArgsConstructor
public class AnswerController {

    private AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerDto> createAnswer(@Valid @RequestBody AnswerDto answerDto) {
        return new ResponseEntity<>(answerService.createAnswer(answerDto).get(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AnswerDto> editAnswer(@Valid @RequestBody AnswerDto answerDto) {
        return ResponseEntity.of(answerService.editAnswer(answerDto));
    }

    @GetMapping("/question/{questionId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<AnswerDto> createAnswer(@PathVariable Long questionId, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                        @RequestParam(value = "page", required = false, defaultValue = "10") int page) {
        return answerService.getAnswersByQuestionId(questionId, pageSize, page);
    }

}
