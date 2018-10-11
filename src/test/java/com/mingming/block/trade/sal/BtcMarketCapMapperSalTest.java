package com.mingming.block.trade.sal;

import com.mingming.block.trade.TradeApplicationTests;
import com.mingming.block.trade.dto.BtcMarketCapDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BtcMarketCapMapperSalTest extends TradeApplicationTests {

    @Autowired
    private BtcMarketCapSal btcMarketCapSal;


    @Test
    public void testDoGet() {
        BtcMarketCapDto btcMarketCapDto = btcMarketCapSal.doGet();
        Assert.assertNotNull(btcMarketCapDto);
        Assert.assertTrue(btcMarketCapDto.getBitCoinPercentageOfMarketCap() > 0);
        System.out.println(btcMarketCapDto);
    }

}
