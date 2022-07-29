package com.so.vos.vote;

import com.so.enums.EntityType;
import com.so.enums.VoteType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VoteVo {

    @NotNull(message = "entityId must not be null")
    private Long entityId;

    @NotNull(message = "entityType must not be null")
    private EntityType entityType;

    @NotNull(message = "voteType must not be null")
    private VoteType voteType;

    @NotNull(message = "voteType must not be null")
    private Long memberId;

    private Double points;
}
