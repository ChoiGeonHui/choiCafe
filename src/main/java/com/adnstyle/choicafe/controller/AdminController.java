package com.adnstyle.choicafe.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    String layout = "templete/layout";

    @RequestMapping("")
    public String adminView(Model model) {

        model.addAttribute("page","admin/adminView");

        return layout;
    }


}
