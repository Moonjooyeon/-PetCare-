package com.petcare.petCarepp.config;

import com.petcare.petCarepp.auth.CustomOAuth2User;
import com.petcare.petCarepp.service.CustomOAuth2UserService;
import com.petcare.petCarepp.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/api-docs/**",
                                "/v3/api-docs/**",
                                "/login/**",
                                "/oauth2/**",
                                "/admin/timeline",
                                "/api/auth/**",
                                "/admin/notice"
                                ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .successHandler((request, response, authentication) -> {
                            CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

                            // JWT 토큰 생성
                            String token = jwtTokenProvider.generateToken(oauthUser.getEmail());

                            // 프론트엔드 리다이렉션 주소로 이동
                            String redirectUrl = "http://localhost:5173/welcome?token=" + token +
                                    "&userName=" + URLEncoder.encode(oauthUser.getName(), StandardCharsets.UTF_8);

                            response.sendRedirect(redirectUrl);
                        })
                );

        return http.build();
    }
}
