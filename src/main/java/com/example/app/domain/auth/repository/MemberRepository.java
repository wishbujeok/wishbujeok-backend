package com.example.app.domain.auth.repository;

import com.example.app.domain.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByMemberId(@Param("memberId") String memberId);
    Optional<Member> findMemberByRefreshToken(String refreshToken);
}
