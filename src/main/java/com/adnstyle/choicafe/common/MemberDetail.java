package com.adnstyle.choicafe.common;

import com.adnstyle.choicafe.domain.GhMember;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class MemberDetail implements UserDetails, OAuth2User, Serializable {

    private static final Long serialVersionUID = 1L;

    private GhMember ghMember;

    private Map<String, Object> attributes;


    public MemberDetail (GhMember ghMember) {
        this.ghMember = ghMember;
    }

    public MemberDetail (GhMember ghMember, Map<String, Object> attributes) {
        this.ghMember = ghMember;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(ghMember.getRole()));
    }

    @Override
    public String getPassword() {
        return ghMember.getPassword();
    }

    @Override
    public String getUsername() {
        return ghMember.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public  Map<String, Object> getAttribute(String name) {
        return attributes;
    }


    @Override
    public String getName() {
        return null;
    }


}
