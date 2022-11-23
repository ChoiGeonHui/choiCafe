package com.adnstyle.choicafe.security;

import com.adnstyle.choicafe.common.MemberDetail;
import com.adnstyle.choicafe.common.SessionMember;
import com.adnstyle.choicafe.service.GhMemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Getter
@Setter
@Component
@Transactional
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final GhMemberService ghMemberService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();

        // 실패횟수 초기화

        MemberDetail ghMember = (MemberDetail) authentication.getPrincipal();
        session.setAttribute("user", new SessionMember(ghMember.getGhMember()));
        response.sendRedirect("/oauth/");
    }
}
