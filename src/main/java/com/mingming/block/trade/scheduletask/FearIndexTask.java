package com.mingming.block.trade.scheduletask;

import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.service.FearIndexService;
import com.mingming.block.trade.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FearIndexTask {

    private final FearIndexService fearIndexService;

    @Autowired
    public FearIndexTask(FearIndexService fearIndexService) {
        this.fearIndexService = fearIndexService;
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void doSchedule() {
        // 中午12点之后执行.
        if (TimeUtils.hourLessThan(12)) {
            return;
        }

        ApiResponseDto<FearIndexDto> recentDtoResponse = fearIndexService.pop();
        FearIndexDto recentDto = recentDtoResponse.getData();
        LocalDate recentDate = recentDto.getDate();
        LocalDate today = LocalDate.now();

        // 如果今天已经添加,就不执行了
        if (recentDate.isEqual(today)) {
            return;
        }
        fearIndexService.store();
    }
}
