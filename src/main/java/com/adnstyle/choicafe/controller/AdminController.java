package com.adnstyle.choicafe.controller;


import com.adnstyle.choicafe.domain.GhBoard;
import com.adnstyle.choicafe.domain.GhMember;
import com.adnstyle.choicafe.service.GhBoardService;
import com.adnstyle.choicafe.service.GhMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    String layout = "templete/layout";

    @Autowired
    GhBoardService ghBoardService;

    @Autowired
    GhMemberService ghMemberService;


    //관리자 페이지 사용자 리스트
    @RequestMapping("")
    public String adminMemberView(Model model) {

        List<GhMember> ghMemberList = ghMemberService.selectMemberList();

        model.addAttribute("ghMemberList", ghMemberList);
        model.addAttribute("page","admin/adminView");

        return layout;
    }



    @RequestMapping("/board")
    public String adminBoardView(GhBoard ghBoard, Model model) {

        ghBoard.setDelYN("Y");

        List<GhBoard> ghBoardList = ghBoardService.selectBoardList(ghBoard);


        model.addAttribute("ghBoardList", ghBoardList);
        model.addAttribute("page", "admin/adminBoardView");


        return layout;
    }




}
