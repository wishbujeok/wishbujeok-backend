package com.example.app.domain.auth.service;

import com.example.app.domain.auth.dto.MemberDto;
import com.example.app.domain.auth.entity.AuthRole;
import com.example.app.domain.auth.entity.Member;
import com.example.app.domain.auth.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<Member> findByMemberId(String memberId){
        return memberRepository.findMemberByMemberId(memberId);
    }
    public Optional<Member> findByRefreshToken(String refreshToken){
        return memberRepository.findMemberByRefreshToken(refreshToken);
    }

    public void save(MemberDto memberDto){
        Member member = Member.builder()
                .memberId(memberDto.getMemberId())
                .nickname(memberDto.getNickname())
                .email(memberDto.getEmail())
                .authRole(AuthRole.ROLE_ADMIN)
                .build();
        memberRepository.save(member);
    }
}
