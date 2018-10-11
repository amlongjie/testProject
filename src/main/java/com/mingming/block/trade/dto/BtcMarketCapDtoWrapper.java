package com.mingming.block.trade.dto;

import com.mingming.block.trade.po.BtcMarketCapPo;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class BtcMarketCapDtoWrapper extends BtcMarketCapDto {

    private LocalDate date;

    public static BtcMarketCapDtoWrapper from(BtcMarketCapDto btcMarketCapDto) {
        BtcMarketCapDtoWrapper btcMarketCapDtoWrapper = new BtcMarketCapDtoWrapper();
        btcMarketCapDtoWrapper.setBitCoinPercentageOfMarketCap(btcMarketCapDto.getBitCoinPercentageOfMarketCap());
        btcMarketCapDtoWrapper.setDate(LocalDate.now());
        return btcMarketCapDtoWrapper;
    }

    public static BtcMarketCapPo to(BtcMarketCapDtoWrapper btcMarketCapDto) {
        BtcMarketCapPo btcMarketCapPo = new BtcMarketCapPo();
        btcMarketCapPo.setPercent((int) (btcMarketCapDto.getBitCoinPercentageOfMarketCap() * 100));
        btcMarketCapPo.setDate(btcMarketCapDto.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        return btcMarketCapPo;
    }

    public static BtcMarketCapDtoWrapper from(BtcMarketCapPo po) {
        BtcMarketCapDtoWrapper btcMarketCapDtoWrapper = new BtcMarketCapDtoWrapper();
        btcMarketCapDtoWrapper.setBitCoinPercentageOfMarketCap(po.getPercent());
        btcMarketCapDtoWrapper.setDate(LocalDate.parse(po.getDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        return btcMarketCapDtoWrapper;
    }
}
