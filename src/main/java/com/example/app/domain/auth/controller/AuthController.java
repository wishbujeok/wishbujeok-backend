package com.example.app.domain.auth.controller;

import com.example.app.domain.auth.dto.JwtTokenDTO;
import com.example.app.domain.auth.service.KakaoOauthService;
import com.example.app.domain.auth.service.MemberService;
import com.example.app.domain.auth.service.OauthService;
import com.example.app.global.common.base.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import static com.example.app.global.common.base.api.ApiResult.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final KakaoOauthService kakaoOauthService;

    private final MemberService memberService;
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
    @GetMapping("/token")
    public ApiResult<JwtTokenDTO> issueAccessToken(@RequestHeader HttpHeaders header){
        String refreshToken = header.getFirst("Authorization");

        return OK(kakaoOauthService.issueAccessToken(refreshToken));
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
