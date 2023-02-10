package com.adnstyle.choicafe.security;

import com.adnstyle.choicafe.common.MemberDetail;
import com.adnstyle.choicafe.common.SessionMember;
import com.adnstyle.choicafe.jwt.JwtProvider;
import com.adnstyle.choicafe.service.GhMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
@Slf4j
@Transactional
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final GhMemberService ghMemberService;

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();

        MemberDetail ghMember = (MemberDetail) authentication.getPrincipal();

        // 실패횟수 초기화
        ghMemberService.failCountReset(ghMember.getGhMember());

        Cookie tokenCookie = new Cookie("Authorization", jwtProvider.createToken(ghMember.getGhMember().getId(), ghMember.getGhMember().getRole()));
        tokenCookie.setMaxAge(2*60*60);
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true); //xss 공격을 막기위한 설정
//        tokenCookie.setSecure(true);
        response.addCookie(tokenCookie);

        session.setAttribute("user", new SessionMember(ghMember.getGhMember()));
        response.sendRedirect("/oauth/");
    }
}
