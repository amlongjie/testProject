package com.mingming.block.trade.sal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class FearGreedIndexSal {

    @Retryable(backoff = @Backoff(value = 2000, multiplier = 2))
    public Document doGet() {
        try {
            return Jsoup.connect("https://alternative.me/crypto/fear-and-greed-index/")
                    .timeout(30000)
                    .get();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
