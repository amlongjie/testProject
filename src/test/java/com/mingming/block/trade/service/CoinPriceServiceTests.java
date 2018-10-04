package com.mingming.block.trade.service;

import com.mingming.block.trade.TradeApplicationTests;
import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.CoinPriceDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CoinPriceServiceTests extends TradeApplicationTests {

    @Autowired
    private CoinPriceService coinPriceService;

    @Test
    public void testContextLoads() {
        Assert.assertNotNull(coinPriceService);
    }

    @Test
    public void testPop() {
        ApiResponseDto<CoinPriceDto> pop = coinPriceService.pop("btc");
        Assert.assertNotNull(pop);
        Assert.assertNotNull(pop.getData());
        Assert.assertEquals(pop.getCode(), 0);
        Assert.assertEquals(pop.getMsg(), "");
        System.out.println(pop);
    }
}
