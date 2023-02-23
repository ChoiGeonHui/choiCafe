package com.adnstyle.choicafe.common;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/board/insertUpdate,/board/delete,/reply/**,/admin/**")
public class ReferrerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String referer = request.getHeader("REFERER");

        String host = request.getHeader("host");
        if (referer == null || referer.length() == 0 || !referer.contains(host)) {
            log.debug("no referer. Who are you? : "+ referer); // 비정상적인 요청
            throw new RuntimeException();
        } else {
            log.debug("referer is not null: "+ referer); // 정상적인 요청
            log.debug("Interceptor : preHandle "+ request.getRequestURI());
        }
        filterChain.doFilter(request, response);
    }
}
