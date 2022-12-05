package com.example.app.auth.service;

import com.example.app.auth.dto.JwtTokenDTO;
import com.example.app.auth.dto.KakaoOauthTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KakaoOauthService implements OauthService {


    public static final String AUTHORIZATION = "authorization";

    public static final String GRANT_TYPE = "grant_type";

    public static final String CLIENT_ID = "client_id";

    public static final String REDIRECT_URI = "redirect_uri";

    public static final String CODE = "code";

    public static final String CLIENT_SECRET = "client_secret";

    @Override
    public void redirectToLoginUri(HttpServletResponse response) {

    }

    @Override
    public JwtTokenDTO login(String authorizeCode) {
//        ResponseEntity<KakaoOauthTokenDTO> response =
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

        ResponseEntity<KakaoOauthTokenDTO> response = restTemplate.postForEntity(kakao.getLoginUri() + "token", httpEntity, KakaoOauthTokenDTO.class);
        return response;
    }
}
