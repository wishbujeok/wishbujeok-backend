package com.example.app.jwt.util;

import com.example.app.auth.dto.JwtTokenDTO;
import com.example.app.auth.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private static final String TOKEN_TYPE = "Bearer";

    @Value("${jwt.key}")
    private String jwtKey;

    public JwtTokenDTO generateToken(Member member){
        Claims claims = Jwts.claims();
        claims.put("email", member.getEmail());
        Key key = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
        return JwtTokenDTO.builder()
                .tokenType(TOKEN_TYPE)
                .accessToken(Jwts.builder()
                        .setClaims(claims)
                        .setSubject("access_token")
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 1시간
                        .signWith(key, SignatureAlgorithm.HS256)
                        .compact())
                .refreshToken(null)
                .build();
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
