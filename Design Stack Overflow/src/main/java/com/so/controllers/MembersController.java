package com.so.controllers;

import com.so.models.Members;
import com.so.services.member.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@AllArgsConstructor
public class MembersController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Members> createQuestion(@RequestBody Members questionVO) {
        return new ResponseEntity<>(memberService.createMember(questionVO).get(), HttpStatus.CREATED);
    }

}
