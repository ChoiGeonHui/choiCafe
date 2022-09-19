package com.adnstyle.choicafe.controller;


import com.adnstyle.choicafe.domain.GhAttach;
import com.adnstyle.choicafe.service.GhAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GhAttachController {


    @Autowired
    GhAttachService ghAttachService;


    /**
     * 파일 다운로드
     * @param seq
     * @return
     * @throws Exception
     */
    @RequestMapping("/download")
    public ResponseEntity<String> download(@RequestParam("seq") Long seq) throws Exception {
        Boolean isDownload = true;
        ghAttachService.download(seq,isDownload);
        return null;
    }

    @RequestMapping("/viewImg")
    public ResponseEntity<String> viewImg(@RequestParam("seq") Long seq) throws Exception {
        Boolean isDownload = false;
        ghAttachService.download(seq,isDownload);
        return null;
    }





}
