package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.common.MemberDetail;
import com.adnstyle.choicafe.common.SessionMember;
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


    @RequestMapping("")
    public String test1 () {
        return "test";
    }


    /**
     * 로그인 페이지
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String loginPage(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/board/list/list";
        }
        model.addAttribute("page", "oauth/loginOAuth");
        return layout;
    }

    /**
     * 비밀번호 찾기페이지
     * @param model
     * @return
     */
    @RequestMapping("/findUser")
    public String findUser(Model model) {
        model.addAttribute("page", "oauth/findUser");
        return layout;
    }


    /** 소셜회원 전환 페이지 */
    @RequestMapping("/transform")
    public String transformMember(Model model) {
        model.addAttribute("page", "/oauth/transformMember");
        return layout;
    }


    /** 소셜회원 전환 api */
    @ResponseBody
    @RequestMapping("/transformMember")
    public Map<String, String> transformMember(GhMember ghMember) {
        Map<String, String> result = new HashMap<>();
        result.put("result", ghMemberService.updateMember(ghMember));
        return result;
    }


    /**
     * 로그인 인증 성공시 항상 이동되는 API
     *
     * @param authentication
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/")
    public Map<String, String> loginA(Authentication authentication, HttpSession session) {
        MemberDetail ghMember = (MemberDetail) authentication.getPrincipal();
        session.setAttribute("user", new SessionMember(ghMember.getGhMember()));
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return result;
    }

    /**
     * 아이디 중복확인 API
     * @param ghMember
     * @return
     */
    @ResponseBody
    @RequestMapping("/idChk")
    public Map<String, String> checkId(GhMember ghMember) {
        String chk = ghMemberService.selectMemberById(ghMember);
        Map<String, String> result = new HashMap<>();
        result.put("result", chk);
        return result;
    }

    /**
     * 회원가입 페이지
     * @param model
     * @return
     */
    @RequestMapping("/sginUp")
    public String sginUpPage(Model model) {
        model.addAttribute("page", "oauth/sginUp");
        return layout;
    }


    /**
     * 사용자 상세보기 페이지
     * @param model
     * @return
     */
    @RequestMapping("/detail")
    public String detail(Model model) {
        model.addAttribute("page", "oauth/updateMember");
        return layout;
    }


    /**
     * 신규 회원 정보 insert API
     * @param ghMember
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertMember")
    public Map<String, String> sginUp(GhMember ghMember) {
        String s = ghMemberService.insertMember(ghMember);
        Map<String, String> result = new HashMap<>();
        result.put("result", s);
        return result;
    }

    /**
     * 비밀번호 변경 API
     * @param ghMember
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePW")
    public Map<String, String> updatePW(GhMember ghMember) {
        Map<String, String> result = new HashMap<>();
        result.put("result", ghMemberService.updatePassword(ghMember));
        return result;
    }

    /**
     * 사용자 접근 제어 페이지
     * @param model
     * @return
     */
    @RequestMapping("/access")
    public String access(Model model) {
        model.addAttribute("page", "oauth/access");
        return layout;
    }


}
