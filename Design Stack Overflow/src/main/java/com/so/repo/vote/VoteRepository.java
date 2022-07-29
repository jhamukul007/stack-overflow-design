package com.so.repo.vote;

import com.so.enums.EntityType;
import com.so.enums.VoteType;
import com.so.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Long> countAllByEntityIdAndEntityTypeAndVoteType(Long entityId, EntityType entityType, VoteType voteType);
}
