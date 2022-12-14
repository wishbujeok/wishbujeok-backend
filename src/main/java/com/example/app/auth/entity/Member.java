package com.example.app.auth.entity;

import com.example.app.base.entity.BaseEntity;
import com.example.util.Util;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.util.Map;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {
    @Column(nullable = false)
    private String memberId;
    @Column(nullable = false)
    private String nickname;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private AuthRole authRole;
    private String refreshToken;

    public Map<String, Object> getAccessTokenClaims() {
        return Util.mapOf(
                "email", getEmail(),
                "nickname", getNickname(),
                "authRole", getAuthRole().name()
        );
    }
}
