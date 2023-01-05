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


    @RequestMapping("/editor")
    public String test() {
        return "comment/testEditor";
    }


    /**
     * 게시물 리스트 보기 - 일반 or 이미지,동영상
     * @param boardHandle
     * @param model
     * @param ghBoard
     * @return
     * @throws Exception
     */
    @RequestMapping("/list/{boardHandle}")
    public String selectBoardList(@PathVariable("boardHandle") String boardHandle, Model model, GhBoard ghBoard) throws Exception {
        Pagination pagination = new Pagination();
        pagination.setCriteria(ghBoard);
        ghBoard.setPageStart();
        pagination.setTotalCount(ghBoardService.selectCount(ghBoard));
        List<GhBoard> ghBoardList = ghBoardService.selectBoardList(ghBoard);

        if (boardHandle.equals("list")) {
            model.addAttribute("page", "board/boardList");
        } else {
            model.addAttribute("page", "board/boardImgVodList");
        }

        model.addAttribute("boardHandle",boardHandle);
        model.addAttribute("paging", pagination);
        model.addAttribute("ghBoardList", ghBoardList);
        return layout;
    }


    /**
     * 선택한 게시물 보기
     * @param boardHandle
     * @param seq
     * @param model
     * @return
     */
    @RequestMapping("/view/{boardHandle}")
    public String BoardView (@PathVariable("boardHandle") String boardHandle, @RequestParam("seq") Long seq, Model model) {

        GhBoard ghBoard = ghBoardService.selectGhBoardBySeq(seq, boardHandle);
        String url = ghBoardService.checkBoardAccess(boardHandle, ghBoard);

        model.addAttribute("page", url);
        model.addAttribute("ghBoard", ghBoard);
        return layout;
    }



    @RequestMapping("/comment")
    public String commentBoard(@RequestParam("seq") Long seq, Model model) {
        GhBoard ghBoard = new GhBoard();
        ghBoard.setParentSeq(seq);
        model.addAttribute("ghBoard", ghBoard);
        model.addAttribute("page", "board/boardInsertUpdate");
        return layout;
    }

    @RequestMapping("/create")
    public String createBoard(Model model) {
        model.addAttribute("page", "board/boardInsertUpdate");
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

    @ResponseBody
    @RequestMapping(value = "/deleteRollback", method = RequestMethod.POST)
    public Map<String, String> deleteRollback(GhBoard ghBoard) {

        String r = ghBoardService.deleteRollback(ghBoard);
        Map<String, String> result = new HashMap<>();

        result.put("result", r);
        return result;
    }


}
