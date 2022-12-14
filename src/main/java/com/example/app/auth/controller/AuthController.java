package com.example.app.auth.controller;

import com.example.app.auth.dto.JwtTokenDTO;
import com.example.app.auth.service.KakaoOauthService;
import com.example.app.auth.service.OauthService;
import com.example.app.base.api.ApiResult;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

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
        System.out.println("access : " + jwtTokenDTO.getAccessToken());
        System.out.println("refresh : " + jwtTokenDTO.getRefreshToken());
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

//    public Authentication getAuthentication(String token) {
//
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtKey)
//                .parseClaimsJws(token)
//                .getBody();
//
//        String userEmail = claims.getSubject();
//        String authRole = claims.get(ROLE_KEY, String.class);
//
//        return new UsernamePasswordAuthenticationToken(userEmail, "", Collections.singleton(new SimpleGrantedAuthority(authRole)));
//    }

//    public boolean validateJwtToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token);
//            return true;
//        } catch (MalformedJwtException e) {
//            throw new MalformedJwtException("MalformedJwtException!!");
//        } catch (ExpiredJwtException e) {
//            throw new RuntimeException();
//        } catch (UnsupportedJwtException e) {
//            throw new UnsupportedJwtException("UnsupportedJwtException!!");
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException();
//        }
//    }
}
