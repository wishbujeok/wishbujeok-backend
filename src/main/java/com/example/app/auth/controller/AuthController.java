package com.example.app.auth.controller;

import com.example.app.auth.dto.JwtTokenDTO;
import com.example.app.auth.service.KakaoOauthService;
import com.example.app.auth.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final KakaoOauthService kakaoOauthService;

    public static final String KAKAO = "kakao";
    // TODO : google 도 구현해보기
//    public static final String GOOGLE = "google";

    @GetMapping("/{provider}/login")
    public JwtTokenDTO login(@PathVariable("provider") String provider,
                             @RequestParam(value = "code") String authorizeCode) {
//        return getProvider(provider).login(authorizeCode);
        return getProvider(provider).login(authorizeCode);
    }

    public OauthService getProvider(String provider) {
        switch (provider) {
            case KAKAO:
                return kakaoOauthService;
//            case GOOGLE:
//                return googleOauthService;
        }

        // 기본 oauth 로그인 값
        return kakaoOauthService;
    }
}
