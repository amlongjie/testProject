package com.mingming.block.trade.dto;

import com.mingming.block.trade.po.CoinPriceMonitorPo;
import lombok.Data;

@Data
public class CoinPriceMonitorDto {

    private Integer lowPrice;
    private Integer highPrice;
    private String symbol;
    private Integer send;

    public static CoinPriceMonitorDto from(CoinPriceMonitorPo coinPriceMonitorPo) {
        CoinPriceMonitorDto dto = new CoinPriceMonitorDto();
        dto.setSymbol(coinPriceMonitorPo.getSymbol());
        dto.setLowPrice(coinPriceMonitorPo.getLowPrice());
        dto.setHighPrice(coinPriceMonitorPo.getHighPrice());
        dto.setSend(coinPriceMonitorPo.getSend());
        return dto;
    }

    public boolean isSended() {
        return send == 1;
    }
}
