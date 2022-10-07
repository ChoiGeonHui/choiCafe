package com.adnstyle.choicafe.service;


import com.adnstyle.choicafe.common.OAuthAttributes;
import com.adnstyle.choicafe.common.SessionMember;
import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.repository.maindb.GhMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final GhMemberRepository ghMemberRepository;

    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName,oAuth2User.getAttributes());

        GhMember ghMember = SaveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionMember(ghMember));
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(ghMember.getRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private GhMember SaveOrUpdate (OAuthAttributes attributes) {
        GhMember ghMember = new GhMember();
        ghMember.setEmail(attributes.getEmail());
        if (ghMemberRepository.selectMember(ghMember) != null) {
            ghMember = ghMemberRepository.selectMember(ghMember);
        } else {
            ghMember = attributes.toEntity();
            ghMemberRepository.insertSocialMember(ghMember);
            ghMember = ghMemberRepository.selectMember(ghMember);

        }

        return ghMember;
    }

}
