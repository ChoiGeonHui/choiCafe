package com.adnstyle.choicafe.service;

import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.jwt.JwtProvider;
import com.adnstyle.choicafe.repository.maindb.GhMemberRepository;
import com.adnstyle.choicafe.security.ErrorCode;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BackOfficeService {

    private final JwtProvider jwtProvider;

    private final GhMemberRepository ghMemberRepository;


    @Transactional
    public Object getResult(HttpServletRequest request, String menuName) {

        Cookie cookies[] = request.getCookies();

        //jwt Filter에서 토큰유효성을 확인하므로 여기서 관련 로직을 구현할 필요 없다.
        String token = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {//Authorization 이름을 가진 쿠키 가져오기
                token = cookie.getValue();
            }
        }

        Claims claims = jwtProvider.parseClaims(token);
        String id = claims.getSubject();
        String role = String.valueOf(claims.get("role"));

        if (ghMemberRepository.checkAuthorization(id,role,menuName) <= 0) {
            request.setAttribute("exception", ErrorCode.ACCESS_DENIED.getCode());
            return null;
        }

        if (menuName.equals("sys")) {
            List<GhMember> list = ghMemberRepository.selectMemberList();
            return list;

        } else {
            return null;
        }
    }



}
