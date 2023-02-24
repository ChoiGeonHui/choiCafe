package com.adnstyle.choicafe.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/board/insertUpdate","/board/delete","/reply/*","/admin/*"})
public class ReferrerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.debug("Filter log ============================");

        String referer = request.getHeader("REFERER");
        String host = request.getHeader("host");
        if (referer == null || referer.length() == 0 || !referer.contains(host)){
            log.debug("NOT_REFERRER _by Filter");
            response.sendError(HttpStatus.BAD_REQUEST.value());
            return;
        }
        filterChain.doFilter(request, response);
    }
}
