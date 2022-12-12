package com.example.app.auth.service;

import com.example.app.auth.dto.JwtTokenDTO;
import com.example.app.auth.dto.KaKaoOauthInfoDto;
import com.example.app.auth.dto.KakaoOauthTokenDTO;
import com.example.app.auth.entity.Member;
import com.example.app.auth.properties.OauthProperties;
import com.example.app.auth.repository.MemberRepository;
import com.example.app.jwt.util.JwtUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoOauthService implements OauthService {

    private final OauthProperties oauthProperties;
    private final RestTemplate restTemplate;
    private final MemberRepository memberRepository;
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
        // 토큰 발급하는 api 호출 후 받아오는 함수
        ResponseEntity<KakaoOauthTokenDTO> kakaoOauthTokenDTOResponseDTO = getProviderToken(authorizeCode);

        System.out.println(kakaoOauthTokenDTOResponseDTO);
        // 제대로 못 받을 경우 예외 처리
        if (!kakaoOauthTokenDTOResponseDTO.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException();
        }
        System.out.println(kakaoOauthTokenDTOResponseDTO);
        // 토큰으로 user 정보를 호출하는 api
        ResponseEntity<String> exchange = getUserInfo(kakaoOauthTokenDTOResponseDTO.getBody());

        System.out.println(exchange);
        if (exchange.getStatusCode().equals(HttpStatus.OK)) {
            KaKaoOauthInfoDto kaKaoOauthInfoDto = gson.fromJson(exchange.getBody(), KaKaoOauthInfoDto.class);

            // 회원번호 => DB의 회원계정
            String memberId = kakao.getRule()
                    .makeFullText(kaKaoOauthInfoDto.getKakaoId().toString());

            // 해당 이메일이 DB상에 존재하는지 확인
            Member findMember = memberRepository.findMemberByMemberId(memberId)
                    .orElse(null);

            // 해당 이메일이 DB상에 존재한다면
            if (!ObjectUtils.isEmpty(findMember)) {
                // 토큰 발급 후 리턴
                return jwtUtil.generateToken(findMember);
            }
            Member newMember = Member.builder()
                    .memberId(memberId)
                    .email(kaKaoOauthInfoDto.getKakaoAccount().getEmail())
                    .build();

            memberRepository.save(newMember);


            return jwtUtil.generateToken(newMember);
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
}
