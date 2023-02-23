package com.adnstyle.choicafe.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Aspect
@Slf4j
@Component
public class AopConfig {


    @Before(value = "execution( * com.adnstyle.choicafe.service.GhBoardService.*(..))")
    public void doCheck () throws Exception {
        //aop는 interceptor와 다르게 기본적으로 HttpServlet Request,Response 객체를 사용할수 없다. 만약 사용을 해야 한다면 아래의 코드처럼 작성해야 함
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();

        log.debug("start log aop");
    }


}
