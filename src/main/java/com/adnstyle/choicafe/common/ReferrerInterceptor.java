package com.adnstyle.choicafe.common;

import com.sun.tools.sjavac.server.Sjavac;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Configuration
public class ReferrerInterceptor implements HandlerInterceptor {

    /**
     * csrf 공격을 막기위한 referrer check code
     * 지정한 api를 호출할때 실행된다.
     * preHandle : 지정한 컨트롤러의 동작 이전에 수행할 동작
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String referer = request.getHeader("REFERER");

        String host = request.getHeader("host");
        if (referer == null || referer.length() == 0 || !referer.contains(host)) {
            log.debug("no referer. Who are you? : "+ referer); // 비정상적인 요청
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"error\":\"diffrent referrer\",\"Msg\":\"출처가 불분명한 요청입니다.\"}");
            return false;
        } else {
            log.debug("referer is not null: "+ referer); // 정상적인 요청
            log.debug("Interceptor : preHandle "+ request.getRequestURI());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
