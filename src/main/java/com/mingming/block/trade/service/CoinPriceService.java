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

import java.util.List;

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
    public ApiResponseDto<CoinPriceDto> crawl(String symbol) {
        CoinPriceDto coinPriceDto = fetchCoinPrice(symbol);
        return ApiResponseDto.success(coinPriceDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<CoinPriceDto> store(String symbol) {
        CoinPriceDto coinPriceDto = fetchCoinPrice(symbol);
        coinPriceDao.insert(coinPriceDto);
        return ApiResponseDto.success(coinPriceDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<CoinPriceDto> pop(String symbol) {
        CoinPriceDto coinPriceDto = coinPriceDao.selectPop(symbol);
        return ApiResponseDto.success(coinPriceDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<List<CoinPriceDto>> search(String symbol) {
        List<CoinPriceDto> coinPriceDtoList = coinPriceDao.selectAll(symbol);
        return ApiResponseDto.success(coinPriceDtoList);
    }


    private CoinPriceDto fetchCoinPrice(String symbol) {
        CoinEnum coinEnum = CoinEnum.findBySymbol(symbol);
        Preconditions.checkArgument(coinEnum != null, "coinEnum is not nullable");
        CoinTickerDto coinTickerDto = coinMarketCapSal.doGet(coinEnum);
        return CoinPriceDto.from(coinTickerDto);
    }


}
