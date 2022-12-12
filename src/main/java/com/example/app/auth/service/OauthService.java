package com.example.app.auth.service;

import com.example.app.auth.dto.JwtTokenDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface OauthService {
    void redirectToLoginUri(HttpServletResponse response);
    JwtTokenDTO login(String authorizeCode);
    String makeLoginUri();
}
