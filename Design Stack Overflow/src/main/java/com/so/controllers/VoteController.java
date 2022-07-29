package com.so.controllers;

import com.so.models.Vote;
import com.so.services.vote.VoteService;
import com.so.vos.vote.VoteVo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/vote")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Vote> addVote(@Valid @RequestBody VoteVo voteVo) {
        return voteService.createVote(voteVo);
    }
}
