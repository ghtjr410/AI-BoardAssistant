package com.seoki.api.member.application.port;

import com.seoki.api.member.domain.Member;
import com.seoki.api.member.fake.FakeMemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberRepositoryTest {

    @DisplayName("회원을 저장할 수 있다.")
    @Test
    void member_create_success() {
        // given
        FakeMemberRepository memberRepository = new FakeMemberRepository();

        Member member = Member.create("test@seoki.com");

        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertThat(savedMember.getId()).isNotNull();
        assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());

        assertThat(memberRepository.findAll()).hasSize(1);
    }

    @DisplayName("존재하지_않는_회원은_조회할_수_없다.")
    @Test
    void member_error_not_found() {
        // given
        FakeMemberRepository memberRepository = new FakeMemberRepository();

        // when
        Optional<Member> member = memberRepository.findById(999L);

        // then
        assertThat(member).isNotPresent();
    }

    @DisplayName("회원이 ID로 정상 조회된다.")
    @Test
    void member_find_by_id_success() {
        // given
        FakeMemberRepository memberRepository = new FakeMemberRepository();
        Member member = Member.create("test@seoki.com");
        Member savedMember = memberRepository.save(member);
        Long savedId = savedMember.getId();

        // when
        Optional<Member> foundMember = memberRepository.findById(savedId);

        // then
        assertThat(foundMember).isPresent();
        assertThat(foundMember.get().getId()).isEqualTo(savedId);
        assertThat(foundMember.get().getEmail()).isEqualTo("test@seoki.com");
    }
}