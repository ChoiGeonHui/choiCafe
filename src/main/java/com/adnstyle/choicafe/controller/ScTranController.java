package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.domain.ScTran;
import com.adnstyle.choicafe.service.ScTranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sctran")
public class ScTranController {

    @Autowired
    private ScTranService scTranService;

    @ResponseBody
    @RequestMapping("/sendMessage")
    public Map<String,String> sendMessage (ScTran scTran) {
        Map<String,String> result = new HashMap<>();
        result.put("result",scTranService.insertScTran(scTran));
        return result;
    }



}
