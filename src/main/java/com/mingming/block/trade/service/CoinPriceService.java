package com.mingming.block.trade.service;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dao.CoinPriceDao;
import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.CoinPriceDto;
import com.mingming.block.trade.dto.CoinTickerDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.sal.CoinMarketCapSal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CoinPriceService {

    private final CoinMarketCapSal coinMarketCapSal;

    private final CoinPriceDao coinPriceDao;

    @Autowired
    public CoinPriceService(CoinMarketCapSal coinMarketCapSal, CoinPriceDao coinPriceMapper) {
        this.coinMarketCapSal = coinMarketCapSal;
        this.coinPriceDao = coinPriceMapper;
    }

    @ExHandlerAnnotation
    public ApiResponseDto<CoinPriceDto> crawl(String coinName) {
        CoinPriceDto coinPriceDto = fetchCoinPrice(coinName);
        return ApiResponseDto.success(coinPriceDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<CoinPriceDto> store(String coinName) {
        CoinPriceDto coinPriceDto = fetchCoinPrice(coinName);
        coinPriceDao.insert(coinPriceDto);
        return ApiResponseDto.success(coinPriceDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<CoinPriceDto> pop(String symbol) {
        CoinPriceDto coinPriceDto = coinPriceDao.selectPop(symbol);
        return ApiResponseDto.success(coinPriceDto);
    }

    private CoinPriceDto fetchCoinPrice(String coinName) {
        CoinEnum coinEnum = CoinEnum.findByName(coinName);
        Preconditions.checkArgument(coinEnum != null, "coinEnum is not nullable");
        CoinTickerDto coinTickerDto = coinMarketCapSal.doGet(coinEnum);
        return CoinPriceDto.from(coinTickerDto);
    }


}
