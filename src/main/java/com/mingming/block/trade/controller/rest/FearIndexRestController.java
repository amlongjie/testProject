package com.mingming.block.trade.controller.rest;

import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.service.FearIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fear")
public class FearIndexRestController {

    private final FearIndexService fearIndexService;

    @Autowired
    public FearIndexRestController(FearIndexService fearIndexService) {
        this.fearIndexService = fearIndexService;
    }

    @GetMapping("/get")
    public ApiResponseDto<FearIndexDto> fetchIndex() {
        return fearIndexService.crawl();
    }

    @GetMapping("/store")
    public ApiResponseDto<Integer> storeIndex() {
        return fearIndexService.store();
    }

}
