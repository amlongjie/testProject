package com.mingming.block.trade.mapper;

import com.mingming.block.trade.TradeApplicationTests;
import com.mingming.block.trade.po.FearIndexPo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FearIndexMapperTests extends TradeApplicationTests {

    @Autowired
    private FearIndexMapper fearIndexMapper;

    @Test
    public void testContextLoads() {
        Assert.assertNotNull(fearIndexMapper);
    }

    @Test
    public void testInsert() {
        FearIndexPo fearIndexPo = new FearIndexPo();
        fearIndexPo.setIndex(27);
        fearIndexPo.setDate("2018-10-04");
        fearIndexPo.setStatus("fear");

        Integer insert = fearIndexMapper.insert(fearIndexPo);
        System.out.println(insert);

        Assert.assertNotNull(fearIndexPo.getId());
        System.out.println(fearIndexPo.getId());
    }
}
