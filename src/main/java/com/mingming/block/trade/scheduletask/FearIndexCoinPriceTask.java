package com.mingming.block.trade.scheduletask;

import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.service.CoinPriceService;
import com.mingming.block.trade.service.FearIndexService;
import com.mingming.block.trade.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FearIndexCoinPriceTask {

    private final FearIndexService fearIndexService;

    private final CoinPriceService coinPriceService;

    @Autowired
    public FearIndexCoinPriceTask(FearIndexService fearIndexService, CoinPriceService coinPriceService) {
        this.fearIndexService = fearIndexService;
        this.coinPriceService = coinPriceService;
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void doSchedule() {
        // 中午12点之后执行.
        if (TimeUtils.hourLessThan(12)) {
            log.info("now is early than 12");
            return;
        }

        ApiResponseDto<Integer> storeResponse = fearIndexService.store();
        if (storeResponse == null || storeResponse.isFailed() || storeResponse.getData() == 0) {
            log.info("today is added");
            return;
        }
        coinPriceService.store(CoinEnum.BitCoin.getSymbol());
        coinPriceService.store(CoinEnum.Eos.getSymbol());
    }
}