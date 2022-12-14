package com.example.app.jwt.filter;

import com.example.app.auth.entity.Member;
import com.example.app.auth.entity.MemberContext;
import com.example.app.auth.service.MemberService;
import com.example.app.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    public static final String AUTHORIZATION = "Authorization";
    public static final String JWT_PREFIX = "Bearer ";
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request.getHeader(AUTHORIZATION));
        log.info("doFilter" + token + "ewlkt");
        log.info(token);
        if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {

            String memberId = jwtUtil.getMemberId(token);
            log.info(memberId);
            Member member = memberService.findByMemberId(memberId).orElseThrow(
                    () -> new UsernameNotFoundException("'%s' Username not found.".formatted(memberId))
            );
            forceAuthentication(member);
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(String fullToken) {
        if (!StringUtils.hasText(fullToken)) {
            return null;
        }
        return fullToken.startsWith(JWT_PREFIX) ? fullToken.substring(JWT_PREFIX.length()) : null;
    }
    private void forceAuthentication(Member member) {
        MemberContext memberContext = new MemberContext(member);

        UsernamePasswordAuthenticationToken authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                        memberContext,
                        null,
                        Collections.singleton(new SimpleGrantedAuthority(member.getAuthRole().name()))
                );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

}
