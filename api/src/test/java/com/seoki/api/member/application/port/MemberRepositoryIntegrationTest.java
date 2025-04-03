package com.seoki.api.member.application.port;

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

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Testcontainers
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@Transactional
class MemberRepositoryIntegrationTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원이 정상적으로 저장된다.")
    void create_member_success() {
        // given
        Member member = Member.create("seoki@test.com");

        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertThat(savedMember.getId()).isNotNull();
        assertThat(savedMember.getEmail()).isEqualTo("seoki@test.com");
    }

    @Test
    @DisplayName("존재하지 않는 회원 조회 시 Optional.empty()를 반환한다.")
    void return_empty_when_member_does_not_exist() {
        // when
        Optional<Member> member = memberRepository.findById(1L);

        // then
        assertThat(member).isNotPresent();
    }

    @Test
    @DisplayName("존재하는 회원을 ID로 조회할 수 있다.")
    void find_member_by_id_success() {
        // given
        Member member = Member.create("seoki@test.com");
        Member savedMember = memberRepository.save(member);
        Long memberId = savedMember.getId();

        // when
        Optional<Member> foundMember = memberRepository.findById(memberId);

        // then
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getId()).isEqualTo(memberId);
        assertThat(foundMember.get().getEmail()).isEqualTo("seoki@test.com");
    }
}