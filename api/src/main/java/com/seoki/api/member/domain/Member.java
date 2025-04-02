package com.seoki.api.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Entity
@Table(name = "member")
@AllArgsConstructor( access = AccessLevel.PRIVATE)
@NoArgsConstructor( access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email")
    private String email;

    public static Member create(String email) {
        return Member.builder()
                .email(email)
                .build();
    }
}
