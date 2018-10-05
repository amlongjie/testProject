package com.mingming.block.trade.facade;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.CoinPriceDto;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.dto.FearIndexWithCoinPriceDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.service.CoinPriceService;
import com.mingming.block.trade.service.FearIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FearIndexWithCoinPriceFacade {

    private final FearIndexService fearIndexService;

    private final CoinPriceService coinPriceService;

    @Autowired
    public FearIndexWithCoinPriceFacade(FearIndexService fearIndexService, CoinPriceService coinPriceService) {
        this.fearIndexService = fearIndexService;
        this.coinPriceService = coinPriceService;
    }

    @ExHandlerAnnotation
    public ApiResponseDto<List<FearIndexWithCoinPriceDto>> queryFearIndexWithCoinPrice() {

        ApiResponseDto<List<FearIndexDto>> fearIndexResponse = fearIndexService.search();
        Preconditions.checkArgument(fearIndexResponse.getCode() == 0, "fearIndex search failed");

        ApiResponseDto<List<CoinPriceDto>> bitCoinResponse = coinPriceService.search(CoinEnum.BitCoin.getSymbol());
        Preconditions.checkArgument(bitCoinResponse.getCode() == 0, "bitCoin price search failed");

        ApiResponseDto<List<CoinPriceDto>> eosResponse = coinPriceService.search(CoinEnum.Eos.getSymbol());
        Preconditions.checkArgument(eosResponse.getCode() == 0, "eos price search failed");

        List<FearIndexDto> fearIndexDtoList = fearIndexResponse.getData();
        List<CoinPriceDto> bitCoinPriceDtoList = bitCoinResponse.getData();
        List<CoinPriceDto> eosPriceDtoList = eosResponse.getData();
        Preconditions.checkArgument(fearIndexDtoList.size() == bitCoinPriceDtoList.size()
                && bitCoinPriceDtoList.size() == eosPriceDtoList.size(), "size not match");

        List<FearIndexWithCoinPriceDto> data = Lists.newArrayListWithCapacity(fearIndexDtoList.size());
        for (int i = 0; i < fearIndexDtoList.size(); i++) {
            FearIndexDto fearIndexDto = fearIndexDtoList.get(i);
            CoinPriceDto bitCoinPrice = bitCoinPriceDtoList.get(i);
            CoinPriceDto eosCoinPrice = eosPriceDtoList.get(i);
            FearIndexWithCoinPriceDto t = FearIndexWithCoinPriceDto.from(fearIndexDto, bitCoinPrice, eosCoinPrice);
            data.add(t);
        }

        return ApiResponseDto.success(data);
    }
}
