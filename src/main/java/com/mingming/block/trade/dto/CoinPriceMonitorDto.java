package com.mingming.block.trade.dto;

import lombok.Data;

@Data
public class CoinPriceMonitorDto {
    private Integer lowPrice;
    private Integer highPrice;
    private String symbol;
}
