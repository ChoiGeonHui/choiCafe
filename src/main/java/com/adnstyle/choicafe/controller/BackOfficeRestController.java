package com.adnstyle.choicafe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/bo/rest")
public class BackOfficeRestController {




    @RequestMapping
    public Map<String, String> updateAdmin() {
        Map<String, String> result = new HashMap<>();

        return result;
    }



}
