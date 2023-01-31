package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.service.BackOfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/bo")
@RequiredArgsConstructor
public class BackOfficeController {

    private final BackOfficeService backOfficeService;

    String layout = "templete/layout";

    @RequestMapping("{menuName}")
    public String menu(@PathVariable("menuName") String menuName,
                       @RequestParam(value = "adminSeq", required = false) Long adminSeq,
                       Model model, HttpServletRequest request) {
        Map<String, Object> map = backOfficeService.getResult(request,adminSeq ,menuName);
        model.addAttribute("menuName", menuName);
        model.addAttribute("page", "admin/bo/"+map.get("page"));
        model.addAttribute("result", map.get("result"));
        model.addAttribute("result2", map.get("result2"));
        return layout;
    }


}
