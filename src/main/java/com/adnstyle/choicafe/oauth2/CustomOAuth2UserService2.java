package com.adnstyle.choicafe.oauth2;


import com.adnstyle.choicafe.common.MemberDetail;
import com.adnstyle.choicafe.common.SessionMember;
import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.domain.Role;
import com.adnstyle.choicafe.repository.maindb.GhMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Transactional
public class CustomOAuth2UserService2 extends DefaultOAuth2UserService {

    private final GhMemberRepository ghMemberRepository;

    private  final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        CustomOAuth2User customOAuth2User = null;

        if (registrationId.equals("google")) {
            customOAuth2User = new GoogleOAuth2User(oAuth2User.getAttributes());
        } else {
            customOAuth2User = new NaverOAuthUser((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String providerId = customOAuth2User.getProviderId();
        String provider = customOAuth2User.getProvider();
        String id = provider+"_"+providerId;
        String password = encoder.encode("aa");
        String email = customOAuth2User.getEmail();
        String name = customOAuth2User.GetName();

        GhMember ghMember = new GhMember();
        ghMember.setEmail(email);
        ghMember.setProvider(provider);
        ghMember.setName(name);

        if (ghMemberRepository.selectMember(ghMember) == null) {
            ghMember.setId(id);
            ghMember.setPassword(password);
            ghMember.setProviderId(providerId);
            ghMember.setRole(Role.SOCIAL.getKey());
            ghMemberRepository.insertSocialMember(ghMember);
            ghMember = ghMemberRepository.selectMember(ghMember);
        } else {
            ghMember = ghMemberRepository.selectMember(ghMember);
        }
        MemberDetail memberDetail = new MemberDetail(ghMember, oAuth2User.getAttributes());

        httpSession.setAttribute("user", new SessionMember(memberDetail.getGhMember()));

        return memberDetail;
    }

}
