package com.example.app.domain.auth.service;

import com.example.app.domain.auth.dto.JwtTokenDTO;
import com.example.app.domain.auth.dto.KaKaoOauthInfoDto;
import com.example.app.domain.auth.dto.KakaoOauthTokenDTO;
import com.example.app.domain.auth.entity.AuthRole;
import com.example.app.domain.auth.entity.Member;
import com.example.app.domain.auth.properties.OauthProperties;
import com.example.app.domain.auth.repository.MemberRepository;
import com.example.app.domain.bujeok.service.BujeokService;
import com.example.app.domain.jwt.util.JwtUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoOauthService implements OauthService {

    private final OauthProperties oauthProperties;
    private final RestTemplate restTemplate;
    private final MemberRepository memberRepository;
    private final BujeokService bujeokService;
    private final Gson gson;
    private final JwtUtil jwtUtil;
    private OauthProperties.Kakao kakao;
    public static final String AUTHORIZATION = "authorization";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String CODE = "code";
    public static final String CLIENT_SECRET = "client_secret";

    @PostConstruct
    public void init() {
        this.kakao = oauthProperties.getKakao();
    }
    @Override
    public void redirectToLoginUri(HttpServletResponse response) {

    }

    @Override
    @Transactional(readOnly = false)
    public JwtTokenDTO login(String authorizeCode) {
        // ?????? ???????????? api ?????? ??? ???????????? ??????
        ResponseEntity<KakaoOauthTokenDTO> kakaoOauthTokenDTOResponseDTO = getProviderToken(authorizeCode);

        // ????????? ??? ?????? ?????? ?????? ??????
        if (!kakaoOauthTokenDTOResponseDTO.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException();
        }
        // ???????????? user ????????? ???????????? api
        ResponseEntity<String> exchange = getUserInfo(kakaoOauthTokenDTOResponseDTO.getBody());

        if (exchange.getStatusCode().equals(HttpStatus.OK)) {
            KaKaoOauthInfoDto kaKaoOauthInfoDto = gson.fromJson(exchange.getBody(), KaKaoOauthInfoDto.class);

            // ???????????? => DB??? ????????????
            String memberId = kakao.getRule()
                    .makeFullText(kaKaoOauthInfoDto.getKakaoId().toString());

            // ?????? ???????????? DB?????? ??????????????? ??????
            Member findMember = memberRepository.findMemberByMemberId(memberId)
                    .orElse(null);



            // ?????? ???????????? DB?????? ???????????????
            if (!ObjectUtils.isEmpty(findMember)) {
                // ?????? ?????? ??? ??????
                JwtTokenDTO jwtTokenDTO = jwtUtil.generateToken(findMember);
                findMember.setRefreshToken(jwtTokenDTO.getRefreshToken());
                memberRepository.save(findMember);
                jwtTokenDTO.setHasBujeok(bujeokService.hasBujeok(findMember.getMemberId()));
                return jwtTokenDTO;
            }

            Member newMember = Member.builder()
                    .memberId(memberId)
                    .nickname(kaKaoOauthInfoDto.getKakaoAccount().getProfile().getNickname())
                    .email(kaKaoOauthInfoDto.getKakaoAccount().getEmail())
                    .authRole(AuthRole.ROLE_USER)
                    .build();
            JwtTokenDTO jwtTokenDTO = jwtUtil.generateToken(newMember);
            newMember.setRefreshToken(jwtTokenDTO.getRefreshToken());
            memberRepository.save(newMember);

            log.info("asdmemberId : "+newMember.getMemberId());
            log.info("asd bujeok : " + bujeokService.hasBujeok(newMember.getMemberId()));

            //????????? ?????? ??????
            jwtTokenDTO.setHasBujeok(bujeokService.hasBujeok(newMember.getMemberId()));

            return jwtTokenDTO;
        }
//
//        throw new LoginException();
        return null;
    }



    @Override
    public String makeLoginUri() {
        return null;
    }

    private ResponseEntity<KakaoOauthTokenDTO> getProviderToken(String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(GRANT_TYPE, kakao.getGrantType());
        params.add(CLIENT_ID, kakao.getClientId());
        params.add(REDIRECT_URI, kakao.getRedirectUri());
        params.add(CODE, code);
        params.add(CLIENT_SECRET,kakao.getClientSecret());

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<KakaoOauthTokenDTO> kakaoOauthTokenDTOResponseDTO = restTemplate.postForEntity(kakao.getLoginUri(), httpEntity, KakaoOauthTokenDTO.class);

        return kakaoOauthTokenDTOResponseDTO;
    }

    private ResponseEntity<String> getUserInfo(KakaoOauthTokenDTO kakaoToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add(AUTHORIZATION, "Bearer " + kakaoToken.getAccessToken());

        ResponseEntity<String> exchange = restTemplate.exchange(kakao.getUserInfoUri(), HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
        return exchange;
    }

    public JwtTokenDTO issueAccessToken(String refreshToken) {
        Member member = memberRepository.findMemberByRefreshToken(refreshToken.substring(7)).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found"));
        return JwtTokenDTO.builder()
                .accessToken(jwtUtil.generateAccessToken(member))
                .refreshToken(null)
                .build();
    }
}
