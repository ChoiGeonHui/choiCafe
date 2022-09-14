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


    @RequestMapping("/download")
    public ResponseEntity<String> download(@RequestParam("seq") Long seq) throws Exception {
        GhAttach ghAttach = new GhAttach();
        ghAttach.setSeq(seq);

        ghAttachService.download(ghAttach);

        return null;

    }


}
