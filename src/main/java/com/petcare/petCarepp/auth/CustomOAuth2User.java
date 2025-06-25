package com.petcare.petCarepp.auth;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.***REMOVED***2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User ***REMOVED***2User;

    public CustomOAuth2User(OAuth2User ***REMOVED***2User) {
        this.***REMOVED***2User = ***REMOVED***2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return ***REMOVED***2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ***REMOVED***2User.getAuthorities();
    }

    @Override
    public String getName() {
        return ***REMOVED***2User.getAttribute("name");
    }

    public String getEmail() {
        return ***REMOVED***2User.getAttribute("email");
    }

    public String getPicture() {
        return ***REMOVED***2User.getAttribute("picture");
    }
}
