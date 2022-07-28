package com.so.services.member;

import com.so.models.Members;
import com.so.services.BaseService;

import java.util.Optional;

public interface MemberService extends BaseService<Members> {
    Optional<Members> createMember(Members member);
}
