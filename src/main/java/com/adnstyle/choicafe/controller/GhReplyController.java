package com.adnstyle.choicafe.controller;


import com.adnstyle.choicafe.domain.GhReply;
import com.adnstyle.choicafe.service.GhReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reply")
public class GhReplyController {

    @Autowired
    GhReplyService ghReplyService;

    @ResponseBody
    @RequestMapping("/insert")
    public Map<String, String> insertReply(GhReply ghReply) {
        Map<String, String> result = new HashMap<>();
        result.put("result", ghReplyService.insertRely(ghReply));
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map<String, String> deleteReply(GhReply ghReply) {
        Map<String, String> result = new HashMap<>();
        result.put("result", ghReplyService.deleteRely(ghReply));
        return result;
    }


}
