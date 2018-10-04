package com.mingming.block.trade.dto;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.po.CoinPricePo;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class CoinPriceDto {
    private String symbol;
    private Integer price;
    private LocalDate date;

    public static CoinPriceDto from(CoinTickerDto coinTickerDto) {
        CoinPriceDto coinPriceDto = new CoinPriceDto();
        coinPriceDto.symbol = coinTickerDto.getData().getSymbol();
        coinPriceDto.price = (int) (coinTickerDto.getData().getQuotes().getQuote().getPrice() * 100);
        coinPriceDto.date = LocalDate.now();
        return coinPriceDto;
    }

    public static CoinPriceDto from(CoinPricePo po) {
        CoinPriceDto coinPriceDto = new CoinPriceDto();
        coinPriceDto.symbol = po.getSymbol();
        coinPriceDto.price = po.getPrice();
        coinPriceDto.date = LocalDate.parse(po.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        return coinPriceDto;
    }

    public static CoinPricePo to(CoinPriceDto coinPriceDto) {
        Preconditions.checkArgument(coinPriceDto != null, "coinPriceDto is null");
        CoinPricePo po = new CoinPricePo();
        po.setSymbol(coinPriceDto.getSymbol());
        po.setDate(coinPriceDto.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        po.setPrice(coinPriceDto.getPrice());
        return po;
    }


}
