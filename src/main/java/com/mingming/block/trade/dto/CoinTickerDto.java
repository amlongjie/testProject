package com.mingming.block.trade.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinTickerDto {

    private Data data;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Data {
        private Long id;
        private String name;
        private String symbol;
        private Quotes quotes;

        @lombok.Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Quotes {

            @JsonProperty("CNY")
            private Quote quote;

            @lombok.Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public class Quote {
                private Double price;
            }
        }
    }
}

