package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.domain.GhBoard;
import com.adnstyle.choicafe.service.GhBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/board")
public class GhBoardController {


    @Autowired
    GhBoardService ghBoardService;

    String layout ="templete/layout";


    @RequestMapping("/count")
    public String test(){
        return layout;
    }


    @RequestMapping("/list")
    public String selectBoardList(Model model){
        List<GhBoard> ghBoardList = ghBoardService.selectBoardList();
        String page = "board/boardList";
        model.addAttribute("page", page);
        model.addAttribute("ghBoardList",ghBoardList);
        return "templete/layout";
    }



    @RequestMapping("/detail")
    public String detailBoard(GhBoard ghBoard){
        return layout;
    }

    @RequestMapping("/create")
    public String createBoard(GhBoard ghBoard,Model model){
        model.addAttribute("page", "board/boardDetail");
        return layout;
    }

    @RequestMapping("/insert")
    public String insertBoard(GhBoard ghBoard,Model model){
//        int i = ghBoardService.insertBoard(ghBoard);
        model.addAttribute("page", "board/boardList");
        return layout;
    }


}
