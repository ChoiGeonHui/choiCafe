package com.adnstyle.choicafe;

import com.adnstyle.choicafe.common.ApplicationUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@ServletComponentScan
@SpringBootApplication
public class ChoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChoiApplication.class, args);
        ApplicationUtil.printRunningLog();
    }

}
