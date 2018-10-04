package com.mingming.block.trade.service;

import com.mingming.block.trade.TradeApplicationTests;
import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.FearIndexDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FearIndexServiceTests extends TradeApplicationTests {

    @Autowired
    private FearIndexService fearIndexService;

    @Test
    public void testContextLoads() {
        Assert.assertNotNull(fearIndexService);
    }

    @Test
    public void testPop() {
        ApiResponseDto<FearIndexDto> pop = fearIndexService.pop();
        Assert.assertNotNull(pop);
        Assert.assertNotNull(pop.getData());
        Assert.assertEquals(pop.getCode(), 0);
        Assert.assertEquals(pop.getMsg(), "");
        System.out.println(pop);
    }
}
