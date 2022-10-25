package com.adnstyle.choicafe.controller;


import com.adnstyle.choicafe.domain.ScTran;
import com.adnstyle.choicafe.domain.SmsCheck;
import com.adnstyle.choicafe.service.SmsCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/smsCheck")
public class SmsCheckController {

    @Autowired
    private SmsCheckService smsCheckService;

    @ResponseBody
    @RequestMapping("/select")
    public  Map<String,String> checkNumber (SmsCheck smsCheck) {
        Map<String,String> result = new HashMap<>();
        result.put("result", smsCheckService.selectSmsCheck(smsCheck));

        return result;
    }



    @ResponseBody
    @RequestMapping("/insert")
    public Map<String,String> sendMessage (SmsCheck smsCheck) {

        Map<String,String> result = new HashMap<>();
        String chk = smsCheckService.insertSmsCheck(smsCheck);

        result.put("result", chk);
        return result;
    }


}
