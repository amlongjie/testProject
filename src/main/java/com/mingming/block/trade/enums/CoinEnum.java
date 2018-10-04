package com.mingming.block.trade.enums;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum CoinEnum {

    BitCoin("1", "btc"), Eos("1765", "eos");

    private String id;
    private String symbol;

    public static CoinEnum findBySymbol(String symbol) {
        Preconditions.checkArgument(StringUtils.isNotBlank(symbol), String.format("symbol is blank, coinName:%s", symbol));
        return Arrays.stream(CoinEnum.values())
                .filter(item -> item.getSymbol().equals(symbol))
                .findAny()
                .orElse(null);
    }
}
