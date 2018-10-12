package com.mingming.block.trade.sal;

import com.mingming.block.trade.enums.CoinEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class OTCBTCPriceSal {

    private static final String BASE_URL = "https://otcbtc.io/sell_offers?currency=%s&fiat_currency=cny&payment_type=all";

    @Retryable(backoff = @Backoff(value = 2000, multiplier = 2))
    public Document doGet(CoinEnum coinEnum) {
        try {
            return Jsoup.connect(String.format(BASE_URL, coinEnum.getSymbol()))
                    .timeout(30000)
                    .validateTLSCertificates(false)
                    .get();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
