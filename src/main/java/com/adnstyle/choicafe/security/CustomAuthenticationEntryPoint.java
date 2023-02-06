package com.adnstyle.choicafe.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        /**
         * request.getAttribute("exception").toString() ==> null(토큰이 존재하지 않다)일 경우 NullPoint에러 발생
         * (String) request.getAttribute("exception") ==> 캐스팅 오류 발생
         */
        String exception = String.valueOf(request.getAttribute("exception"));


        // 토큰이 존재하지 않는 경우
        if (exception.equals("null")|| exception == null) {
            setResponse(response, ErrorCode.UNKNOWN_ERROR);
            response.sendRedirect("/oauth/login");// 지정된 url로 이동
        }

        else if (exception.equals(ErrorCode.COOKIE_NOT_FOUND.getCode() + "")) {
            setResponse(response, ErrorCode.COOKIE_NOT_FOUND);
            response.sendRedirect("/logout");// 지정된 url로 이동
        }

        //잘못된 타입의 토큰인 경우
        else if (exception.equals(ErrorCode.WRONG_TYPE_TOKEN.getCode() + "")) {
            setResponse(response, ErrorCode.WRONG_TYPE_TOKEN);
            response.sendRedirect("/logout");// 지정된 url로 이동
        }

        //토큰 만료된 경우
        else if (exception.equals(ErrorCode.EXPIRED_TOKEN.getCode() + "")) {
            setResponse(response, ErrorCode.EXPIRED_TOKEN);
            response.sendRedirect("/logout");// 지정된 url로 이동

        }

        //지원되지 않는 토큰인 경우
        else if (exception.equals(ErrorCode.UNSUPPORTED_TOKEN.getCode() + "")) {
            setResponse(response, ErrorCode.UNSUPPORTED_TOKEN);
            response.sendRedirect("/logout");// 지정된 url로 이동

        }
        //지원되지 않는 토큰인 경우
        else if (exception.equals(ErrorCode.NO_IMAGE.getCode() + "")) {
            setResponse(response, ErrorCode.NO_IMAGE);
        }

        else {
            setResponse(response, ErrorCode.ACCESS_DENIED);
            response.sendRedirect("/logout");// 지정된 url로 이동
        }
    }

    /**
     * 한글 출력을 위해 getWriter() 사용
     */
    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        try {
            responseJson.put("message", errorCode.getMessage());
            responseJson.put("code", errorCode.getCode());
            response.getWriter().print(responseJson);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}