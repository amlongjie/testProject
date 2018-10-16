package com.mingming.block.trade.controller.rest;

import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.OtcCoinPriceDto;
import com.mingming.block.trade.service.OtcCoinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otc")
public class OtcCoinPriceRestController {

    private final OtcCoinPriceService otcCoinPriceService;

    @Autowired
    public OtcCoinPriceRestController(OtcCoinPriceService otcCoinPriceService) {
        this.otcCoinPriceService = otcCoinPriceService;
    }

    @GetMapping("/get/{name}")
    public ApiResponseVO<OtcCoinPriceDto> fetch(@PathVariable(name = "name") String name) {
        return otcCoinPriceService.crawl(name);
    }

}
