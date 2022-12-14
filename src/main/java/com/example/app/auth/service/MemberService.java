package com.example.app.auth.service;

import com.example.app.auth.entity.Member;
import com.example.app.auth.repository.MemberRepository;
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
}
