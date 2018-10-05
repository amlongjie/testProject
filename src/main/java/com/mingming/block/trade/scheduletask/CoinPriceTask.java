package com.mingming.block.trade.scheduletask;

import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.CoinPriceDto;
import com.mingming.block.trade.service.CoinPriceService;
import com.mingming.block.trade.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class CoinPriceTask {

    private final CoinPriceService coinPriceService;

    @Autowired
    public CoinPriceTask(CoinPriceService coinPriceService) {
        this.coinPriceService = coinPriceService;
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void doSchedule() {
        // 中午12点之后执行.
        if (TimeUtils.hourLessThan(12)) {
            log.info("now is early than 12");
            return;
        }

        doStore("btc");
        doStore("eos");

    }

    private void doStore(String symbol) {

        ApiResponseDto<CoinPriceDto> recentDtoResponse = coinPriceService.pop(symbol);
        CoinPriceDto recentDto = recentDtoResponse.getData();
        LocalDate recentDate = recentDto.getDate();
        LocalDate today = LocalDate.now();

        // 如果今天已经添加,就不执行了
        if (recentDate.isEqual(today)) {
            log.info(String.format("today has stored, recentDto:%s", recentDto));
            return;
        }
        coinPriceService.store(symbol);
    }
}
