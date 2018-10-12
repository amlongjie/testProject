package com.mingming.block.trade.scheduletask;

import com.mingming.block.trade.sal.OTCBTCPriceSal;
import com.mingming.block.trade.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Slf4j
@Component
public class PriceMonitorTask {

    private final OTCBTCPriceSal otcbtcPriceSal;

    private final MailService mailService;

    @Autowired
    public PriceMonitorTask(@NotNull OTCBTCPriceSal otcbtcPriceSal,
                            @NotNull MailService mailService) {
        this.otcbtcPriceSal = otcbtcPriceSal;
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void doSchedule() {
        
    }
}
