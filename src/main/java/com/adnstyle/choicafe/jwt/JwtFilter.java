package com.adnstyle.choicafe.jwt;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;


    /**
     * 가지고 있는 토큰의 검사하는 메서드
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie cookies[] = ((HttpServletRequest) servletRequest).getCookies();
        if (cookies == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String path = ((HttpServletRequest) servletRequest).getServletPath();
        if (path.startsWith("/oauth/") || path.startsWith("/logout") || path.startsWith("/error") || path.startsWith("/valid")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {

            String token = "";
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {//Authorization 이름을 가진 쿠키 가져오기
                    token = cookie.getValue();
                }
            }
            String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            if (StringUtils.hasText(token) && jwtProvider.validateToken(token, request)) {
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Security context에 인증 정보를 저장했습니다, uri: {" + requestURI + "}");
            } else {
                log.debug("유효한 Jwt 토큰이 없습니다, uri: {" + requestURI + "}");
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }


}
