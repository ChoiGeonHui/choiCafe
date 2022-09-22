package com.adnstyle.choicafe.controller;

import com.adnstyle.choicafe.common.Pagination;
import com.adnstyle.choicafe.domain.GhBoard;
import com.adnstyle.choicafe.service.GhBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class GhBoardController {


    @Autowired
    GhBoardService ghBoardService;

    String layout = "templete/layout";


    @RequestMapping("/count")
    public String test() {
        return layout;
    }


    @RequestMapping("/list/{boardHandle}")
    public String selectBoardList(@PathVariable("boardHandle") String boardHandle, Model model, GhBoard ghBoard) throws Exception {
        Pagination pagination = new Pagination();
        pagination.setCriteria(ghBoard);
        ghBoard.setPageStart();
        pagination.setTotalCount(ghBoardService.selectCount(ghBoard));
        List<GhBoard> ghBoardList = ghBoardService.selectBoardList(ghBoard);

        if (boardHandle.equals("list")){
            model.addAttribute("page", "board/boardList");
        } else {
            model.addAttribute("page", "board/boardImgVodList");
        }

        model.addAttribute("boardHandle",boardHandle);
        model.addAttribute("paging", pagination);
        model.addAttribute("ghBoardList", ghBoardList);
        return layout;
    }

    @RequestMapping("/view")
    public String BoardView (@RequestParam("seq") Long seq, Model model) {
        GhBoard ghBoard = ghBoardService.selectGhBoardBySeq(seq);
        model.addAttribute("ghBoard", ghBoard);
        model.addAttribute("page", "board/boardView");
        return layout;
    }


    @RequestMapping("/detail")
    public String detailBoard(@RequestParam("seq") Long seq, Model model) {
        GhBoard ghBoard = ghBoardService.selectGhBoardBySeq(seq);
        model.addAttribute("ghBoard", ghBoard);
        model.addAttribute("page", "board/boardDetail");
        return layout;
    }


    @RequestMapping("/comment")
    public String commentBoard(@RequestParam("seq") Long seq, Model model) {
        GhBoard ghBoard = new GhBoard();
        ghBoard.setParentSeq(seq);
        model.addAttribute("ghBoard", ghBoard);
        model.addAttribute("page", "board/boardDetail");
        return layout;
    }

    @RequestMapping("/create")
    public String createBoard(Model model) {
        model.addAttribute("page", "board/boardDetail");
        return layout;
    }

    @RequestMapping("/insertUpdate")
    public String cuBoard(GhBoard ghBoard, @RequestPart("fileList") List<MultipartFile> file) {
        ghBoardService.cuBoard(ghBoard, file);
        return "redirect:/board/list/list";
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, String> deleteBoard(@RequestParam("seq[]") List<Long> seq) {
        String total = ghBoardService.deleteBoard(seq);
        Map<String, String> result = new HashMap<>();
        result.put("result", total);
        return result;
    }


}
