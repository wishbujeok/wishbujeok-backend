package com.example.app.jwt.filter;

import com.example.app.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    public static final String AUTHORIZATION = "authorization";
    public static final String JWT_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request.getHeader(AUTHORIZATION));
        log.info("doFilter" + token + "ewlkt");
        log.info(token);
        if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {

            Authentication authentication = jwtUtil.getAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(String fullToken) {
        if (!StringUtils.hasText(fullToken)) {
            return null;
        }

        return fullToken.startsWith(JWT_PREFIX) ? fullToken.substring(JWT_PREFIX.length()) : null;
    }
}
