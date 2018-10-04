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
    private String name;

    public static CoinEnum findByName(String coinName) {
        Preconditions.checkArgument(StringUtils.isNotBlank(coinName), String.format("coinName is blank, coinName:%s", coinName));
        return Arrays.stream(CoinEnum.values())
                .filter(item -> item.getName().equals(coinName))
                .findAny()
                .orElse(null);
    }
}
