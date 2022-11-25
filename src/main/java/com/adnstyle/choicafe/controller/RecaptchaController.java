package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.service.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RecaptchaController {

    @Autowired
    private RecaptchaService recaptchaService;

    @RequestMapping("/valid-recaptcha")
    public String validRecaptcha(HttpServletRequest request) {
        String response = request.getParameter("g-recaptcha-response");
        boolean isRecaptcha = recaptchaService.verify(response);

        if (isRecaptcha) {
            return "success";
        } else {
            return "fail";
        }

    }


}
