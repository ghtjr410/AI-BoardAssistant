package com.seoki.api.member.application.command;

import com.seoki.api.member.application.port.MemberRepository;
import com.seoki.api.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    public final MemberRepository memberRepository;

    @Transactional
    public long create(String email) {
        Member member = Member.create(email);
        memberRepository.save(member);

        return member.getId();
    }
}
