package com.so.services.member.impl;

import com.so.models.Members;
import com.so.repo.member.MemberRepo;
import com.so.services.member.MemberService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberRepo memberRepo;

    @Override
    public Members saveOrUpdate(Members value) {
        return memberRepo.save(value);
    }

    @Override
    public Members saveOrUpdateTx(Members value) {
        return  memberRepo.save(value);
    }

    @Override
    public Optional<Members> findById(@NonNull Long id) {
        return  memberRepo.findById(id);
    }

    @Override
    public <S extends Members> List<S> saveAll(List<S> entities) {
        return memberRepo.saveAll(entities);
    }

    @Override
    public Optional<Members> createMember(Members member) {
        return Optional.of(memberRepo.save(member));
    }
}
