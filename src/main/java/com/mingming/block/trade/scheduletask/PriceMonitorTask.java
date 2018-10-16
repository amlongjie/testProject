package com.mingming.block.trade.scheduletask;

import com.google.common.collect.Lists;
import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.CoinPriceMonitorDto;
import com.mingming.block.trade.dto.OtcCoinPriceDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.service.CoinPriceMonitorService;
import com.mingming.block.trade.service.MailService;
import com.mingming.block.trade.service.OtcCoinPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PriceMonitorTask {


    private final MailService mailService;

    private final CoinPriceMonitorService coinPriceMonitorService;

    private final OtcCoinPriceService otcCoinPriceService;

    @Autowired
    public PriceMonitorTask(MailService mailService,
                            CoinPriceMonitorService coinPriceMonitorService,
                            OtcCoinPriceService otcCoinPriceService) {
        this.mailService = mailService;
        this.coinPriceMonitorService = coinPriceMonitorService;
        this.otcCoinPriceService = otcCoinPriceService;
    }


    @Scheduled(cron = "0 */5 * * * ?")
    public void doSchedule() {
        StringBuilder body = new StringBuilder();
        List<CoinEnum> sendList = Lists.newArrayList();
        for (CoinEnum coinEnum : CoinEnum.values()) {
            ApiResponseVO<CoinPriceMonitorDto> fetchResult = coinPriceMonitorService.fetch(coinEnum.getSymbol());
            // 查询失败
            if (fetchResult.isFailed()) {
                continue;
            }
            CoinPriceMonitorDto monitorDto = fetchResult.getData();
            // 已经发送过邮件
            if (monitorDto.isSended()) {
                continue;
            }

            ApiResponseVO<OtcCoinPriceDto> crawlResult = otcCoinPriceService.crawl(coinEnum.getSymbol());
            if (crawlResult.isFailed()) {
                continue;
            }
            OtcCoinPriceDto curPrice = crawlResult.getData();

            if (curPrice.getPrice() <= monitorDto.getLowPrice()) {
                body.append(String.format("%s - %s lower than %s\n", coinEnum.getSymbol(), curPrice.getPrice(), monitorDto.getLowPrice()));
                sendList.add(coinEnum);
                continue;
            }
            if (curPrice.getPrice() >= monitorDto.getHighPrice()) {
                body.append(String.format("%s - %s higher than %s\n", coinEnum.getSymbol(), curPrice.getPrice(), monitorDto.getHighPrice()));
                sendList.add(coinEnum);
            }
        }
        if (body.length() > 0) {
            ApiResponseVO<Boolean> sendResult = mailService.send(body.toString());
            if (sendResult.isFailed()) {
                return;
            }
            coinPriceMonitorService.updateSendStatus(sendList);
        }
        log.info(String.format("monitor end, body:%s", body.toString()));
    }
}
