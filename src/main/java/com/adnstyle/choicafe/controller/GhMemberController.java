package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.service.GhMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/oauth")
public class GhMemberController {

    @Autowired
    GhMemberService ghMemberService;

    String layout = "templete/layout";

    @RequestMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("page", "oauth/loginOAuth");
        return layout;
    }


    /**
     * 로그인 인증 성공시 항상 이동되는 api
     * @param authentication
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/")
    public Map<String, String> loginA(Authentication authentication, HttpSession session) {
        GhMember ghMember = (GhMember) authentication.getPrincipal();
        session.setAttribute("user", ghMember);
        Map<String, String> result = new HashMap<>();
        result.put("result","success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/idChk")
    public Map<String,String> checkId(GhMember ghMember) {
        String chk = ghMemberService.selectMemberById(ghMember);
        Map<String,String> result = new HashMap<>();
        result.put("result",chk);
        return result;
    }


    @RequestMapping("/sginUp")
    public String sginUpPage(Model model) {
        model.addAttribute("page", "oauth/sginUp");
        return layout;
    }

    @ResponseBody
    @RequestMapping("/insertMember")
    public Map<String, String> sginUp(GhMember ghMember) {
        String s = ghMemberService.insertMember(ghMember);
        Map<String, String> result = new HashMap<>();
        result.put("result",s);
        return result;
    }

    @RequestMapping("/access")
    public String access(Model model) {
        model.addAttribute("page", "oauth/access");
        return layout;
    }


}
