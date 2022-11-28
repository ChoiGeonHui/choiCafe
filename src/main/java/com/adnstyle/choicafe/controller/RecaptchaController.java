package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.service.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/valid")
public class RecaptchaController {

    @Autowired
    private RecaptchaService recaptchaService;


    /**
     * google reCaptcha V2
     * @param request
     * @return
     */
    @RequestMapping("/recaptcha")
    public String validRecaptcha(HttpServletRequest request) {
        String response = request.getParameter("g-recaptcha-response");
        boolean isRecaptcha = recaptchaService.verify(response);

        if (isRecaptcha) {
            return "success";
        } else {
            return "fail";
        }

    }


    /**
     * reCaptcha V3
     * @param token
     * @return
     */
    @RequestMapping("/recaptchaV3")
    public String validRecaptcha3(String token) {
        return recaptchaService.reCaptchaV3(token);
    }


}
