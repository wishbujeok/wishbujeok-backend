package com.example.app.auth.controller;

import com.example.app.auth.dto.JwtTokenDTO;
import com.example.app.auth.service.KakaoOauthService;
import com.example.app.auth.service.OauthService;
import com.example.app.base.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.app.base.api.ApiResult.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final KakaoOauthService kakaoOauthService;

    public static final String KAKAO = "kakao";
    // TODO : google 도 구현해보기
//    public static final String GOOGLE = "google";

    @GetMapping("/{provider}/login")
    public ApiResult<JwtTokenDTO> login(@PathVariable("provider") String provider,
                                        @RequestParam(value = "code") String authorizeCode) {

        JwtTokenDTO jwtTokenDTO = getProvider(provider).login(authorizeCode);
        System.out.println(jwtTokenDTO);
        return OK(jwtTokenDTO);
    }

    public OauthService getProvider(String provider) {
        switch (provider) {
            case KAKAO:
                return kakaoOauthService;
//                TODO : GOOGLE 로그인 추가하기
//            case GOOGLE:
//                return googleOauthService;
        }

        return kakaoOauthService;
    }
}
