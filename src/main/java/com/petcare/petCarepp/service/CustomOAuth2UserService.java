package com.petcare.petCarepp.service;


import com.petcare.User.entity.User;
import com.petcare.User.Repository.UserRepository;
import com.petcare.petCarepp.auth.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(request);

        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

        userRepository.findByEmail(email).orElseGet(() ->
                userRepository.save(User.builder()
                        .email(email)
                        .password("") // OAuth 사용자이므로 패스워드는 빈 문자열
                        .oauthProvider("google")
                        .name(name)
                        .profileImage(null)
                        .role("USER")
                        .build())
        );

        return new CustomOAuth2User(user); // OAuth2User를 래핑해서 반환
    }
}
