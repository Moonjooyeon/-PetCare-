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
        OAuth2User oauth2User = super.loadUser(request);

        // ✅ 구글은 email, name 바로 접근 가능
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // ✅ DB 저장 or 조회
        User savedUser = userRepository.findByEmail(email).orElseGet(() ->
                userRepository.save(User.builder()
                        .email(email)
                        .password("") // OAuth 사용자 비번 없음
                        .oauthProvider("google")
                        .name(name != null ? name : "이름없음")
                        .profileImage(null)
                        .role("USER")
                        .build())
        );

        // ✅ CustomOAuth2User로 감싸서 userId 전달까지 완료
        return new CustomOAuth2User(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getName(),
                savedUser.getRole(),
                oauth2User.getAttributes()
        );
    }


}
