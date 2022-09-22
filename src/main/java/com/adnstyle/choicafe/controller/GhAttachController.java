package com.adnstyle.choicafe.controller;


import com.adnstyle.choicafe.domain.GhAttach;
import com.adnstyle.choicafe.service.GhAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GhAttachController {


    @Autowired
    GhAttachService ghAttachService;


    /**
     * 파일 다운로드, 파일 불러오기
     * @param ghAttach
     * @return
     * @throws Exception
     */
    @RequestMapping("/files/{handing}")
    public ResponseEntity<String> download(@PathVariable("handing") String handing, GhAttach ghAttach) throws Exception {
        ghAttachService.download(ghAttach,handing);
        return null;
    }


}
