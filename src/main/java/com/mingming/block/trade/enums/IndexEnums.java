package com.mingming.block.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum IndexEnums {

    Fear("fear"), Foi("foi"), Bvix("bvix"), Dpc("dpc");

    private String symbol;

    public static IndexEnums fetchBySymbol(String symbol) {
        return Arrays.stream(IndexEnums.values())
                .filter(item -> item.getSymbol().equals(symbol))
                .findAny().orElse(null);
    }
}
