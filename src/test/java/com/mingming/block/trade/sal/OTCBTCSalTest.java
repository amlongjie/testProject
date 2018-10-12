package com.mingming.block.trade.sal;

import com.mingming.block.trade.TradeApplicationTests;
import com.mingming.block.trade.dto.BtcMarketCapDto;
import com.mingming.block.trade.enums.CoinEnum;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OTCBTCSalTest extends TradeApplicationTests {

    @Autowired
    private OTCBTCPriceSal otcbtcPriceSal;


    @Test
    public void testDoGet() {
        CoinEnum coinEnum = CoinEnum.Eos;
        Document document = otcbtcPriceSal.doGet(coinEnum);
        String price = document.getElementsByClass("list-content")
                .get(0)
                .getElementsByClass("price")
                .get(0)
                .text()
                .replace("Price", "")
                .replace("CNY", "")
                .replace("/", "")
                .replace(coinEnum.getSymbol().toUpperCase(), "")
                .trim();

        System.out.print(price);
    }

}
