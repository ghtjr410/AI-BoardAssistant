package com.seoki.api.member.application.command;

import com.seoki.api.member.application.port.MemberRepository;
import com.seoki.api.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.BDDMockito.*;

@Testcontainers
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@Transactional
class MemberCommandServiceIntegrationTest {

    @Autowired
    MemberCommandService memberCommandService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원이 정상적으로 등록된다.")
    void create_member_success() {
        // given
        String email = "test@seoki.com";

        // when
        long memberId = memberCommandService.create(email);

        // then
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        assertThat(member.getEmail()).isEqualTo(email);
    }
}