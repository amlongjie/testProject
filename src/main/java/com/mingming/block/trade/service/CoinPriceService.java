package com.mingming.block.trade.service;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dao.CoinPriceDao;
import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.CoinPriceDto;
import com.mingming.block.trade.dto.CoinTickerDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.sal.CoinPriceSal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoinPriceService {

    private final CoinPriceSal coinPriceSal;

    private final CoinPriceDao coinPriceDao;

    @Autowired
    public CoinPriceService(CoinPriceSal coinPriceSal, CoinPriceDao coinPriceMapper) {
        this.coinPriceSal = coinPriceSal;
        this.coinPriceDao = coinPriceMapper;
    }

    @ExHandlerAnnotation
    public ApiResponseVO<CoinPriceDto> crawl(String symbol) {
        CoinPriceDto coinPriceDto = fetchCoinPrice(symbol);
        return ApiResponseVO.success(coinPriceDto);
    }

    @ExHandlerAnnotation
    public ApiResponseVO<Integer> store(String symbol) {
        CoinPriceDto coinPriceDto = fetchCoinPrice(symbol);
        int affect = coinPriceDao.insert(coinPriceDto);
        return ApiResponseVO.success(affect);
    }

    @ExHandlerAnnotation
    public ApiResponseVO<List<CoinPriceDto>> search(String symbol) {
        List<CoinPriceDto> coinPriceDtoList = coinPriceDao.selectAll(symbol);
        return ApiResponseVO.success(coinPriceDtoList);
    }


    private CoinPriceDto fetchCoinPrice(String symbol) {
        CoinEnum coinEnum = CoinEnum.findBySymbol(symbol);
        Preconditions.checkArgument(coinEnum != null, "coinEnum is not nullable");
        CoinTickerDto coinTickerDto = coinPriceSal.doGet(coinEnum);
        return CoinPriceDto.from(coinTickerDto);
    }


}
