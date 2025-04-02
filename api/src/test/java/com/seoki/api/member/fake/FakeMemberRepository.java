package com.seoki.api.member.fake;

import com.seoki.api.member.application.port.MemberRepository;
import com.seoki.api.member.domain.Member;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMemberRepository implements MemberRepository {

    private final Map<Long, Member> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Member save(Member member) {
        if (member.getId() == null) {
            long id = sequence.getAndIncrement();
            setId(member, id);
        }
        storage.put(member.getId(), member);

        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(storage.values());
    }

    private void setId(Member member, long id) {
        try {
            Field field = Member.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(member, id);

        } catch (Exception e) {
            throw new RuntimeException("FakeMemberRepository - id 설정 실패: ", e);
        }
    }
}
