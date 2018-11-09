package com.mingming.block.trade.sal.dpc;

import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.sal.dpc.model.DpcIndexDto;
import net.dongliu.requests.Requests;
import net.dongliu.requests.json.TypeInfer;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class DpcIndexSal {

    private static final String URL = "https://api.kumiclub.com/api/index/darpal/dpc-1d";

    @Retryable(backoff = @Backoff(value = 2000, multiplier = 2))
    public DpcIndexDto doGet() {
        return Requests.get(URL)
                .timeout(30000)
                .send()
                .readToJson(new TypeInfer<ApiResponseVO<DpcIndexDto>>() {
                }).getData();
    }
}
