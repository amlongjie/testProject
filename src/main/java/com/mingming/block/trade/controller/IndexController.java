package com.mingming.block.trade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/")
    public String alive() {
        return "index";
    }

}
