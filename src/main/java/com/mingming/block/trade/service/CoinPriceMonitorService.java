package com.mingming.block.trade.service;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dao.CoinPriceMonitorDao;
import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.CoinPriceMonitorDto;
import com.mingming.block.trade.enums.CoinEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinPriceMonitorService {

    private final CoinPriceMonitorDao coinPriceMonitorDao;

    @Autowired
    public CoinPriceMonitorService(CoinPriceMonitorDao coinPriceMonitorDao) {
        this.coinPriceMonitorDao = coinPriceMonitorDao;
    }

    @ExHandlerAnnotation
    public ApiResponseVO<CoinPriceMonitorDto> fetch(String symbol) {
        CoinEnum coinEnum = CoinEnum.findBySymbol(symbol);
        CoinPriceMonitorDto coinPriceMonitorDto = coinPriceMonitorDao.selectBySymbol(coinEnum);
        return ApiResponseVO.success(coinPriceMonitorDto);
    }

    @ExHandlerAnnotation
    public ApiResponseVO<List<CoinPriceMonitorDto>> search() {
        return ApiResponseVO.success(coinPriceMonitorDao.selectAll());
    }

    @ExHandlerAnnotation
    public ApiResponseVO<Integer> updateSendStatus(List<CoinEnum> sendList) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(sendList), "send list is empty");
        List<String> symbolList = sendList.stream().map(CoinEnum::getSymbol).collect(Collectors.toList());
        int affect = coinPriceMonitorDao.updateSendStatusBySymbolList(symbolList);
        return ApiResponseVO.success(affect);
    }
}
