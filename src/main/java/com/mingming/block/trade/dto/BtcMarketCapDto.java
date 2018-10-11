package com.mingming.block.trade.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BtcMarketCapDto {

    @JsonProperty("bitcoin_percentage_of_market_cap")
    private double bitCoinPercentageOfMarketCap;

}
