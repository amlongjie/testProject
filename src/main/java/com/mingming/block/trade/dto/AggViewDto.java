package com.mingming.block.trade.dto;

import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class AggViewDto {

    private String date;
    private Integer btcPrice;
    private Integer eosPrice;
    private Integer index;
    private String status;
    private String btcCap;

    public static AggViewDto from(FearIndexDto fearIndexDto, CoinPriceDto bitCoinPrice, CoinPriceDto eosCoinPrice, BtcMarketCapDtoWrapper btcMarketCapDto) {
        AggViewDto aggViewDto = new AggViewDto();
        aggViewDto.setDate(fearIndexDto.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        aggViewDto.setIndex(fearIndexDto.getIndex());
        aggViewDto.setStatus(fearIndexDto.getStatus());
        aggViewDto.setBtcPrice(bitCoinPrice.getPrice());
        aggViewDto.setEosPrice(eosCoinPrice.getPrice());
        aggViewDto.setBtcCap(String.format("%.2f", btcMarketCapDto.getBitCoinPercentageOfMarketCap() / 100));
        return aggViewDto;
    }

}
