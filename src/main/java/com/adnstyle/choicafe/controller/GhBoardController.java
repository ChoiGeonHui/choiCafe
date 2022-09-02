package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.domain.GhBoard;
import com.adnstyle.choicafe.service.GhBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String selectBoardList(@RequestParam(value = "searchWord",required = false)String searchWord, Model model){
        List<GhBoard> ghBoardList = ghBoardService.selectBoardList();
        String page = "board/boardList";
        model.addAttribute("page", page);
        model.addAttribute("ghBoardList",ghBoardList);
        return "templete/layout";
    }



    @RequestMapping("/detail")
    public String detailBoard(@RequestParam("seq") Long seq, Model model) {
        GhBoard ghBoard = ghBoardService.selectGhBoardBySeq(seq);
        model.addAttribute("ghBoard", ghBoard);
        model.addAttribute("page", "board/boardDetail");
        return layout;
    }

    @RequestMapping("/create")
    public String createBoard(GhBoard ghBoard,Model model){
        model.addAttribute("page", "board/boardDetail");
        return layout;
    }

    @RequestMapping("/insert")
    public String insertBoard(GhBoard ghBoard,Model model){
        int i = ghBoardService.insertBoard(ghBoard);
        return "redirect:/board/list";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String,String> insertBoard(@RequestParam("seq") Long seq){
        String total = ghBoardService.deleteBoard(seq);
        Map<String,String> result = new HashMap<>();
        result.put("result",total);
        return result;
    }


}
