package com.so.controllers;

import com.so.dtos.QuestionDto;
import com.so.services.question.QuestionService;
import com.so.services.tags.TagsService;
import com.so.vos.QuestionVO;
import com.so.vos.TagVo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tag")
@AllArgsConstructor
public class TagController {
    private final TagsService tagsService;

    @PostMapping
    public ResponseEntity<TagVo> createQuestion(@Valid @RequestBody TagVo tagVo){
        return new ResponseEntity<>(tagsService.createTags(tagVo).get(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TagVo> searchTag(@RequestParam("title") String title){
        return ResponseEntity.of(tagsService.searchTags(title));
    }

    @GetMapping("/all")
    @ResponseBody
    public List<TagVo> searchTag(){
        return tagsService.getAll();
    }
}
