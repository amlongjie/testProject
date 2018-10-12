package com.mingming.block.trade.service;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dao.CoinPriceMonitorDao;
import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.CoinPriceDto;
import com.mingming.block.trade.dto.CoinRealTimePriceDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.sal.OTCBTCPriceSal;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CoinRealTimePriceMonitorService {

    private final OTCBTCPriceSal otcbtcPriceSal;

    private final CoinPriceMonitorDao coinPriceMonitorDao;

    @Autowired
    public CoinRealTimePriceMonitorService(OTCBTCPriceSal otcbtcPriceSal,
                                           CoinPriceMonitorDao coinPriceMonitorDao) {
        this.otcbtcPriceSal = otcbtcPriceSal;
        this.coinPriceMonitorDao = coinPriceMonitorDao;
    }

    @ExHandlerAnnotation
    public ApiResponseVO<CoinPriceMonitorDto> fetch(CoinEnum coinEnum) {
        Preconditions.checkArgument(coinEnum != null, "coinEnum is null");
        int price = fetchPriceByCoin(coinEnum);

        CoinPriceDto coinPriceDto = coinPriceMonitorDao.selectBySymbol(coinEnum);


    }

    private int fetchPriceByCoin(CoinEnum coinEnum) {
        Document document = otcbtcPriceSal.doGet(coinEnum);
        String stringPrice = document.getElementsByClass("list-content")
                .get(0)
                .getElementsByClass("price")
                .get(0)
                .text()
                .replace("Price", "")
                .replace("CNY", "")
                .replace("/", "")
                .replace(coinEnum.getSymbol().toUpperCase(), "")
                .trim();
        return new BigDecimal(stringPrice).multiply(BigDecimal.TEN).intValue();
    }
}
