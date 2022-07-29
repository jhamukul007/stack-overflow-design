package com.so.services.vote;

import com.so.enums.VoteType;
import com.so.models.Vote;
import com.so.services.BaseService;
import com.so.vos.vote.VoteVo;

import java.util.Optional;

public interface VoteService extends BaseService<Vote> {
    Optional<Vote> createVote(VoteVo vo);
    Optional<Long> countVoteByQuestionId(Long questionId, VoteType voteType);
}
