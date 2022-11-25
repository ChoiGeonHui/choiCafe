package com.adnstyle.choicafe.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("google.recaptcha.key")
public class ReCaptchaSettings {

    private String site;
    private String secret;
    private String url;

}
