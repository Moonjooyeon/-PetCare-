package com.petcare.petCarepp.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;


@Getter
public class CustomOAuth2User implements OAuth2User {

    private final Long id;

    private final String email;
    private final String name;
    private final String role;
    private final Map<String, Object> attributes;

    // ✅ [1] DB 조회 기반 생성자 (예: JwtTokenProvider 에서 사용)
    public CustomOAuth2User(Long id, String email, String name, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.attributes = Map.of(); // ⚠️ attributes가 null이면 getAttributes에서 NPE 뜸. 빈 Map으로 처리.
    }

    public CustomOAuth2User(Long id, String email, String name, String role, Map<String, Object> attributes) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.attributes = attributes != null ? attributes : Map.of(); // NPE 방지
    }




    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> role);
    }

    @Override
    public String getName() {
        return name;
    }
    public Long getId() {
        return id;
    }

}
