package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.service.BackOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/bo")
@RequiredArgsConstructor
public class BackOfficeController {

    private final BackOfficeService backOfficeService;

    String layout = "templete/layout";

    @RequestMapping("{menuName}")
    public String menu(@PathVariable("menuName") String menuName, Model model, HttpServletRequest request) {
        model.addAttribute("menuName", menuName);
        model.addAttribute("page", "admin/bo/"+menuName);
        model.addAttribute("result", backOfficeService.getResult(request, menuName));
        return layout;
    }


}
