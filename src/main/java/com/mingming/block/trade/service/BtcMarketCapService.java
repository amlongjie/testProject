package com.mingming.block.trade.service;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dao.BtcMarketCapDao;
import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.BtcMarketCapDto;
import com.mingming.block.trade.dto.BtcMarketCapDtoWrapper;
import com.mingming.block.trade.sal.BtcMarketCapSal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BtcMarketCapService {

    private final BtcMarketCapSal btcMarketCapSal;

    private final BtcMarketCapDao btcMarketCapDao;

    @Autowired
    public BtcMarketCapService(BtcMarketCapSal btcMarketCapSal, BtcMarketCapDao btcMarketCapDao) {
        this.btcMarketCapSal = btcMarketCapSal;
        this.btcMarketCapDao = btcMarketCapDao;
    }

    @ExHandlerAnnotation
    public ApiResponseVO<BtcMarketCapDtoWrapper> crawl() {
        BtcMarketCapDtoWrapper btcMarketCapDtoWrapper = doFetch();
        return ApiResponseVO.success(btcMarketCapDtoWrapper);
    }

    @ExHandlerAnnotation
    public ApiResponseVO<Integer> store() {
        BtcMarketCapDtoWrapper btcMarketCapDto = doFetch();
        int affect = btcMarketCapDao.insert(btcMarketCapDto);
        return ApiResponseVO.success(affect);
    }

    @ExHandlerAnnotation
    public ApiResponseVO<List<BtcMarketCapDtoWrapper>> search() {
        List<BtcMarketCapDtoWrapper> coinPriceDtoList = btcMarketCapDao.selectAll();
        return ApiResponseVO.success(coinPriceDtoList);
    }

    private BtcMarketCapDtoWrapper doFetch() {
        BtcMarketCapDto btcMarketCapDto = btcMarketCapSal.doGet();
        Preconditions.checkArgument(
                btcMarketCapDto != null && btcMarketCapDto.getBitCoinPercentageOfMarketCap() > 0,
                "btc market cap crawl failed");
        return BtcMarketCapDtoWrapper.from(btcMarketCapDto);
    }
}
