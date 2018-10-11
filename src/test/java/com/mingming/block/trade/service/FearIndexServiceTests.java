package com.mingming.block.trade.service;

import com.mingming.block.trade.TradeApplicationTests;
import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.FearIndexDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class FearIndexServiceTests extends TradeApplicationTests {

    @Autowired
    private FearIndexService fearIndexService;
    
    @Test
    public void testCrawl() {
        ApiResponseVO<FearIndexDto> crawlResponse = fearIndexService.crawl();
        Assert.assertNotNull(crawlResponse);
        Assert.assertEquals(0, crawlResponse.getCode());
        Assert.assertEquals("", crawlResponse.getMsg());

        FearIndexDto dto = crawlResponse.getData();
        Assert.assertEquals(LocalDate.now(), dto.getDate());
    }

}
