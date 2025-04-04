package com.seoki.api.member.application.port;

import com.seoki.api.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(long id);
    Member save(Member member);
}
