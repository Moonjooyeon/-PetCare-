package com.petcare.petCarepp.jwt;

import com.petcare.User.Repository.UserRepository;
import com.petcare.petCarepp.auth.CustomOAuth2User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserRepository userRepository;

    private final String SECRET_KEY = "mysupersecurejwtsecretkeythatworks1234";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String generateToken(Long userId,String email) {
        Date now = new Date();
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    // ✅ Authentication 생성
    public Authentication getAuthentication(String token) {
        String email = getEmailFromToken(token);

        // 유저 DB 조회
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + email));

        CustomOAuth2User principal = new CustomOAuth2User(
                user.getId(),             // ✅ 이거 추가
                user.getEmail(),
                user.getName(),
                user.getRole()            // 또는 "ROLE_USER"
        );


        return new UsernamePasswordAuthenticationToken(
                principal,
                null,
                principal.getAuthorities()
        );
    }
}
