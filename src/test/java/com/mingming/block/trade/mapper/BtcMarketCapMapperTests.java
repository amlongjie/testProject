package com.mingming.block.trade.mapper;

import com.mingming.block.trade.TradeApplicationTests;
import com.mingming.block.trade.po.BtcMarketCapPo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BtcMarketCapMapperTests extends TradeApplicationTests {

    @Autowired
    private BtcMarketCapMapper btcMarketCapMapper;

    @Test
    public void testAdd() {
        Integer affect = insert();
        Assert.assertNotNull(affect);
        Assert.assertEquals(1, (int) affect);
    }

    @Test
    public void testSearch() {
        insert();
        insert();
        List<BtcMarketCapPo> btcMarketCapPos = btcMarketCapMapper.selectAll();
        Assert.assertNotNull(btcMarketCapPos);
        Assert.assertTrue(btcMarketCapPos.size() > 0);
    }

    @Test
    public void testPop() {
        insert();
        BtcMarketCapPo btcMarketCapPo = btcMarketCapMapper.selectPop();
        Assert.assertNotNull(btcMarketCapPo);
    }

    private Integer insert() {
        BtcMarketCapPo btcMarketCapPo = new BtcMarketCapPo();
        btcMarketCapPo.setPercent(4312);
        btcMarketCapPo.setDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        return btcMarketCapMapper.insert(btcMarketCapPo);
    }
}
