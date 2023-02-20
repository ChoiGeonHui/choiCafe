package com.adnstyle.choicafe.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ReferrerInterceptor())
                .addPathPatterns("/board/insertUpdate","/board/delete","/reply/**","/admin/**") // 등록할 컨트롤러 url
                .excludePathPatterns("/static/**","/error",
                        "/**/*.png", "/**/*.gif", "/**/*.svg",
                        "/**/*.jpg", "/**/*.html", "/**/*.css",
                        "/**/*.js", "/**/*.jsp", "/oauth/**",
                        "/sctran/**","/smsCheck/**","/valid/**","/token/**"); //제외할 컨트롤러 url
    }
}
