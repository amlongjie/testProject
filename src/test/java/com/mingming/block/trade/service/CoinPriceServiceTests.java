package com.mingming.block.trade.service;

import com.mingming.block.trade.TradeApplicationTests;
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

}
