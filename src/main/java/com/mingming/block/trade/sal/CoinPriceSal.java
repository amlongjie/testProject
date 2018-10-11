package com.mingming.block.trade.sal;

import com.mingming.block.trade.dto.CoinTickerDto;
import com.mingming.block.trade.enums.CoinEnum;
import lombok.extern.slf4j.Slf4j;
import net.dongliu.requests.Requests;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoinPriceSal {

    private static final String BASE_URL = "https://api.coinmarketcap.com/v2/ticker/%s/?convert=CNY";

    @Retryable(backoff = @Backoff(value = 2000, multiplier = 2))
    public CoinTickerDto doGet(CoinEnum coinEnum) {
        String id = coinEnum.getId();
        String url = String.format(BASE_URL, id);
        return Requests.get(url)
                .timeout(30000)
                .send()
                .readToJson(CoinTickerDto.class);
    }

}
