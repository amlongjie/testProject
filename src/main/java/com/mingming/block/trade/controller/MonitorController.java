package com.mingming.block.trade.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorController {

    @GetMapping("/alive")
    public String alive() {
        return "{\"status\":\"alive\"}";
    }

}
