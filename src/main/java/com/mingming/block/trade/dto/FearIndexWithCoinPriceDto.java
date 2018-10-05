package com.mingming.block.trade.dto;

import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class FearIndexWithCoinPriceDto {

    private String date;
    private Integer btcPrice;
    private Integer eosPrice;
    private Integer index;
    private String status;

    public static FearIndexWithCoinPriceDto from(FearIndexDto fearIndexDto, CoinPriceDto bitCoinPrice, CoinPriceDto eosCoinPrice) {
        FearIndexWithCoinPriceDto fearIndexWithCoinPriceDto = new FearIndexWithCoinPriceDto();
        fearIndexWithCoinPriceDto.setDate(fearIndexDto.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        fearIndexWithCoinPriceDto.setIndex(fearIndexDto.getIndex());
        fearIndexWithCoinPriceDto.setStatus(fearIndexDto.getStatus());
        fearIndexWithCoinPriceDto.setBtcPrice(bitCoinPrice.getPrice());
        fearIndexWithCoinPriceDto.setEosPrice(eosCoinPrice.getPrice());
        return fearIndexWithCoinPriceDto;
    }
    
}
