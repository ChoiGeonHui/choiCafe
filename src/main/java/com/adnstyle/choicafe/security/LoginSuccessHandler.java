package com.adnstyle.choicafe.security;

import com.adnstyle.choicafe.common.MemberDetail;
import com.adnstyle.choicafe.common.SessionMember;
import com.adnstyle.choicafe.service.GhMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Transactional
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final GhMemberService ghMemberService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = null;

        // 실패횟수 초기화


        // 로그인 세션 지우기

        //Redirect URL 작업
//        resultRedirectStrategy(request, response, authentication);

        MemberDetail ghMember = (MemberDetail) authentication.getPrincipal();
        session.setAttribute("user", new SessionMember(ghMember.getGhMember()));
        Map<String, String> result = new HashMap<>();

        request.getRequestDispatcher("/oauth/").forward(request, response);
    }
}
