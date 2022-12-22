package com.example.app.domain.auth.dto;

import com.example.app.domain.auth.entity.AuthRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
public class MemberDto {
    private String memberId;
    private String nickname;
    private String email;

}
