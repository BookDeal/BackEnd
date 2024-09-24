package com.BookDeal.BookDeal;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "your_secret_key"; // 비밀 키
    private final long VALIDITY_IN_MS = 3600000; // 1시간

    public String createToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_IN_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            // 토큰을 파싱하여 claims 추출
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY) // 비밀 키 설정
                    .parseClaimsJws(token) // 토큰 파싱
                    .getBody();

            // claims에서 사용자 이름 가져오기 (옵션)
            String username = claims.getSubject();

            // 토큰의 만료 시간을 확인하여 유효성을 검사
            return !isTokenExpired(claims);

        } catch (ExpiredJwtException e) {
            // 토큰이 만료된 경우
            System.out.println("토큰이 만료되었습니다: " + e.getMessage());
        } catch (SignatureException e) {
            // 서명이 유효하지 않은 경우
            System.out.println("서명이 유효하지 않습니다: " + e.getMessage());
        } catch (Exception e) {
            // 기타 예외 처리
            System.out.println("토큰 검증 중 오류 발생: " + e.getMessage());
        }

        return false; // 유효하지 않은 경우 false 반환
    }

    // 만료 시간 체크 메서드
    private boolean isTokenExpired(Claims claims) {
        Date expiration = claims.getExpiration();
        return expiration.before(new Date()); // 현재 시간과 비교하여 만료 여부 확인
    }

    public String getSecretKey() {
        return SECRET_KEY;
    }
}
