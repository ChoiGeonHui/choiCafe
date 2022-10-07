package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.domain.ScLog;
import com.adnstyle.choicafe.service.ScLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sub")
public class ScLogController {

    @Autowired
    private ScLogService scLogService;

    @ResponseBody
    @RequestMapping("/test")
    public List<ScLog> selectList (){
        List<ScLog> list = scLogService.selectList();
        return list;
    }
}
