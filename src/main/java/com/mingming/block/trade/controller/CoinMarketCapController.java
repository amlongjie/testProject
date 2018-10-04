package com.mingming.block.trade.controller;

import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.CoinPriceDto;
import com.mingming.block.trade.service.CoinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coin")
public class CoinMarketCapController {

    private final CoinPriceService coinPriceService;

    @Autowired
    public CoinMarketCapController(CoinPriceService coinPriceService) {
        this.coinPriceService = coinPriceService;
    }

    @GetMapping("/get/{name}")
    public ApiResponseDto<CoinPriceDto> fetchTicker(@PathVariable(name = "name") String name) {
        return coinPriceService.crawl(name);
    }


    @GetMapping("/store/{name}")
    public ApiResponseDto<CoinPriceDto> storeTicker(@PathVariable(name = "name") String name) {
        return coinPriceService.store(name);
    }

    @GetMapping("/pop/{name}")
    public ApiResponseDto<CoinPriceDto> popTicker(@PathVariable(name = "name") String name) {
        return coinPriceService.pop(name);
    }
}
