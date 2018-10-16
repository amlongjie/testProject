package com.mingming.block.trade.service;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.OtcCoinPriceDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.sal.OTCBTCPriceSal;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OtcCoinPriceService {

    private final OTCBTCPriceSal otcbtcPriceSal;

    @Autowired
    public OtcCoinPriceService(OTCBTCPriceSal otcbtcPriceSal) {
        this.otcbtcPriceSal = otcbtcPriceSal;
    }

    @ExHandlerAnnotation
    public ApiResponseVO<OtcCoinPriceDto> crawl(String name) {
        OtcCoinPriceDto otcCoinPriceDto = fetchCoinPrice(name);
        return ApiResponseVO.success(otcCoinPriceDto);
    }

    private OtcCoinPriceDto fetchCoinPrice(String symbol) {
        CoinEnum coinEnum = CoinEnum.findBySymbol(symbol);
        Preconditions.checkArgument(coinEnum != null, "coinEnum is not nullable");
        Document document = otcbtcPriceSal.doGet(coinEnum);
        String stringPrice = document.getElementsByClass("list-content")
                .get(0)
                .getElementsByClass("price")
                .get(0)
                .text()
                .replace("Price", "")
                .replace("CNY", "")
                .replace("/", "")
                .replace(",", "")
                .replace(coinEnum.getSymbol().toUpperCase(), "")
                .trim();
        return new OtcCoinPriceDto(new BigDecimal(stringPrice).multiply(BigDecimal.TEN).multiply(BigDecimal.TEN).intValue());
    }

}
