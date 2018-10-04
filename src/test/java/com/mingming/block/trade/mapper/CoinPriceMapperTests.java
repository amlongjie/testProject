package com.mingming.block.trade.mapper;

import com.mingming.block.trade.TradeApplicationTests;
import com.mingming.block.trade.po.CoinPricePo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CoinPriceMapperTests extends TradeApplicationTests {

    @Autowired
    private CoinPriceMapper coinPriceMapper;

    @Test
    public void testContextLoads() {
        Assert.assertNotNull(coinPriceMapper);
    }

    @Test
    public void testInsert() {

        CoinPricePo coinPricePo = new CoinPricePo();
        coinPricePo.setPrice(123);
        coinPricePo.setSymbol("eos");
        coinPricePo.setDate("2018-10-05");

        Integer insert = coinPriceMapper.insert(coinPricePo);
        System.out.println(insert);

        Assert.assertNotNull(coinPricePo.getId());
        System.out.println(coinPricePo.getId());
        
    }
}
