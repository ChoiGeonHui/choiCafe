package com.adnstyle.choicafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/",""})
    public String main() {
        return "redirect:/board/list/list";
    }

}
