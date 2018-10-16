package com.mingming.block.trade.controller.rest;

import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.CoinPriceMonitorDto;
import com.mingming.block.trade.service.CoinPriceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monitor")
public class CoinPriceMonitorRestController {

    private final CoinPriceMonitorService coinPriceMonitorService;

    @Autowired
    public CoinPriceMonitorRestController(CoinPriceMonitorService coinPriceMonitorService) {
        this.coinPriceMonitorService = coinPriceMonitorService;
    }

    @GetMapping("/get/{name}")
    public ApiResponseVO<CoinPriceMonitorDto> fetch(@PathVariable(name = "name") String symbol) {
        return coinPriceMonitorService.fetch(symbol);
    }


    @GetMapping("/search")
    public ApiResponseVO<List<CoinPriceMonitorDto>> search() {
        return coinPriceMonitorService.search();
    }

}
