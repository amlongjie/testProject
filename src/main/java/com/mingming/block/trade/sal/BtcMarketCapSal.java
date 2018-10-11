package com.mingming.block.trade.sal;

import com.mingming.block.trade.dto.BtcMarketCapDto;
import net.dongliu.requests.Requests;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class BtcMarketCapSal {
    private static final String URL = "https://s2.coinmarketcap.com/generated/stats/global.json";

    @Retryable(backoff = @Backoff(value = 2000, multiplier = 2))
    public BtcMarketCapDto doGet() {
        return Requests.get(URL)
                .timeout(30000)
                .send()
                .readToJson(BtcMarketCapDto.class);
    }
}
