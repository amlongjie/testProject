package com.mingming.block.trade.controller;

import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.FearGreedIndexDto;
import com.mingming.block.trade.service.FearIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fear")
public class FearGreedIndexController {

    private final FearIndexService fearIndexService;

    @Autowired
    public FearGreedIndexController(FearIndexService fearIndexService) {
        this.fearIndexService = fearIndexService;
    }

    @GetMapping("/get")
    public ApiResponseDto<FearGreedIndexDto> fetchIndex() {
        return fearIndexService.crawl();
    }

    @GetMapping("/store")
    public ApiResponseDto<FearGreedIndexDto> storeIndex() {
        return fearIndexService.store();
    }

    @GetMapping("/pop")
    public ApiResponseDto<FearGreedIndexDto> popIndex() {
        return fearIndexService.pop();
    }

}
