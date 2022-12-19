package com.example.app.jwt.util;

import com.example.app.auth.dto.JwtTokenDTO;
import com.example.app.auth.entity.Member;
import com.example.util.Util;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtil {

    // 1시간 단위
    public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60* 60;
    @Value("${jwt.key}")
    private String jwtKey;

    public JwtTokenDTO generateToken(Member member){
        return JwtTokenDTO.builder()
                .accessToken(generateAccessToken(member))
                .refreshToken(generateRefreshToken())
                .build();
    }
    public String generateAccessToken(Member member){
        Key key = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(member.getMemberId())
                .setClaims(Util.mapOf(member.getAccessTokenClaims(), "type", "ATK"))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)) // 1시간
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateRefreshToken(){
        Key key = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setClaims(Util.mapOf("type", "RTK"))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 24 * 365)) // 1년
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getMemberId(String accessToken){
        Claims claims = parseClaims(accessToken);
        return claims.get("memberId", String.class);
    }

    public String getType(String accessToken){
        Claims claims = parseClaims(accessToken);
        return claims.get("type", String.class);
    }
    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("type") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        String authRole = claims.get("authRole", String.class);
        String memberId = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(memberId, "", Collections.singleton(new SimpleGrantedAuthority(authRole)));
    }
    private Claims parseClaims(String accessToken) {
        Key key = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        Key key = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            throw new ExpiredJwtException(null, null, "Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw new ExpiredJwtException(null, null, "Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }
//    /**
//     * 토큰 유효여부 확인
//     */
//    public Boolean isValidToken(String token, UserDetails userDetails) {
//        String username = getUsernameFromToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    /**
//     * 토큰의 Claim 디코딩
//     */
//    private Claims getAllClaims(String token) {
//        log.info("getAllClaims token = {}", token);
//        return Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    /**
//     * Claim 에서 username 가져오기
//     */
//    public String getUsernameFromToken(String token) {
//        String username = String.valueOf(getAllClaims(token).get("username"));
//        log.info("getUsernameFormToken subject = {}", username);
//        return username;
//    }
//
//    /**
//     * 토큰 만료기한 가져오기
//     */
//    public Date getExpirationDate(String token) {
//        Claims claims = getAllClaims(token);
//        return claims.getExpiration();
//    }
//
//    /**
//     * 토큰이 만료되었는지
//     */
//    private boolean isTokenExpired(String token) {
//        return getExpirationDate(token).before(new Date());
//    }
}
