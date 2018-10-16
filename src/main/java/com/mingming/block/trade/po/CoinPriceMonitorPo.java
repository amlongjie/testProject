package com.mingming.block.trade.po;

import lombok.Data;

import java.util.Date;

@Data
public class CoinPriceMonitorPo {
    private Integer id;
    private Integer lowPrice;
    private Integer highPrice;
    private String symbol;
    private Date updateTime;
    private Integer send;
}
