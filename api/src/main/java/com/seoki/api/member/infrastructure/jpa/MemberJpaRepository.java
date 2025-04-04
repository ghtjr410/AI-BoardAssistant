package com.seoki.api.member.infrastructure.jpa;

import com.seoki.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
