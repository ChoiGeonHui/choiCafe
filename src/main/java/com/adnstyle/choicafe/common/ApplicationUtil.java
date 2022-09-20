package com.adnstyle.choicafe.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationUtil implements ApplicationContextAware {

    private static ApplicationContext ac;


    public static void printRunningLog() {
        log.info("http://localhost:8800/board/list");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationUtil.ac = applicationContext;
    }
}
