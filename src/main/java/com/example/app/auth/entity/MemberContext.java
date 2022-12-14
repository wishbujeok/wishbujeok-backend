package com.example.app.auth.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collections;

@Getter
@JsonIncludeProperties({"id", "createDate", "modifyDate", "memberId", "nickname", "email", "authRole", "refreshToken"})
public class MemberContext extends User {
    private final long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final String memberId;
    private final String nickname;
    private final  String email;
    private final  AuthRole authRole;
    private final  String refreshToken;
    public MemberContext(Member member) {
        super(member.getMemberId(), "", Collections.singleton(new SimpleGrantedAuthority(member.getAuthRole().name())));

        id = member.getId();
        createDate = member.getCreateDate();
        modifyDate = member.getModifyDate();
        memberId = member.getMemberId();
        nickname = member.getNickname();
        email = member.getEmail();
        authRole = member.getAuthRole();
        refreshToken = member.getRefreshToken();
    }
}
