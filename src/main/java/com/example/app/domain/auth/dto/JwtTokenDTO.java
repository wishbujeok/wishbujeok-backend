package com.example.app.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenDTO {

    private String accessToken;
    private String refreshToken;
    private boolean hasBujeok;
    @Builder
    public JwtTokenDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
