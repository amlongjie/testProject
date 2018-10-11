package com.mingming.block.trade.controller.rest;


import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.BtcMarketCapDtoWrapper;
import com.mingming.block.trade.service.BtcMarketCapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cap")
public class BtcMarketCapRestController {

    @Autowired
    private BtcMarketCapService btcMarketCapService;

    @RequestMapping("/get")
    public ApiResponseVO<BtcMarketCapDtoWrapper> fetch() {
        return btcMarketCapService.crawl();
    }

    @RequestMapping("/store")
    public ApiResponseVO<Integer> store() {
        return btcMarketCapService.store();
    }

    @RequestMapping("/search")
    public ApiResponseVO<List<BtcMarketCapDtoWrapper>> search() {
        return btcMarketCapService.search();
    }
}
