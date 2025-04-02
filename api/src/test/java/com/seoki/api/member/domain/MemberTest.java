package com.seoki.api.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.BDDMockito.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MemberTest {

    @Test
    @DisplayName("회원정보를 정상적으로 생성한다.")
    void create_member_success() {
        // Given
        String email = "test@email.com";

        // When
        Member member = Member.create(email);

        // Then
        assertThat(member).isNotNull();
        assertThat(member.getId()).isNull();
        assertThat(member.getEmail()).isEqualTo(email);
    }
}