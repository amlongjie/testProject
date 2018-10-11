package com.mingming.block.trade.facade;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dto.*;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.service.BtcMarketCapService;
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
    private BtcMarketCapService btcMarketCapService;

    @Autowired
    public FearIndexWithCoinPriceFacade(FearIndexService fearIndexService,
                                        CoinPriceService coinPriceService,
                                        BtcMarketCapService btcMarketCapService) {
        this.fearIndexService = fearIndexService;
        this.coinPriceService = coinPriceService;
        this.btcMarketCapService = btcMarketCapService;
    }

    @ExHandlerAnnotation
    public ApiResponseVO<List<AggViewDto>> queryFearIndexWithCoinPrice() {

        ApiResponseVO<List<FearIndexDto>> fearIndexResponse = fearIndexService.search();
        Preconditions.checkArgument(fearIndexResponse.getCode() == 0, "fearIndex search failed");

        ApiResponseVO<List<CoinPriceDto>> bitCoinResponse = coinPriceService.search(CoinEnum.BitCoin.getSymbol());
        Preconditions.checkArgument(bitCoinResponse.getCode() == 0, "bitCoin price search failed");

        ApiResponseVO<List<CoinPriceDto>> eosResponse = coinPriceService.search(CoinEnum.Eos.getSymbol());
        Preconditions.checkArgument(eosResponse.getCode() == 0, "eos price search failed");

        ApiResponseVO<List<BtcMarketCapDtoWrapper>> btcCap = btcMarketCapService.search();
        Preconditions.checkArgument(btcCap.getCode() == 0, "btc cap search failed");

        List<FearIndexDto> fearIndexDtoList = fearIndexResponse.getData();
        List<CoinPriceDto> bitCoinPriceDtoList = bitCoinResponse.getData();
        List<CoinPriceDto> eosPriceDtoList = eosResponse.getData();
        List<BtcMarketCapDtoWrapper> btcMarketCapDtoList = btcCap.getData();
        Preconditions.checkArgument(fearIndexDtoList.size() == bitCoinPriceDtoList.size()
                && bitCoinPriceDtoList.size() == eosPriceDtoList.size()
                && eosPriceDtoList.size() == btcMarketCapDtoList.size(), "size not match");

        List<AggViewDto> data = Lists.newArrayListWithCapacity(fearIndexDtoList.size());
        for (int i = 0; i < fearIndexDtoList.size(); i++) {
            FearIndexDto fearIndexDto = fearIndexDtoList.get(i);
            CoinPriceDto bitCoinPrice = bitCoinPriceDtoList.get(i);
            CoinPriceDto eosCoinPrice = eosPriceDtoList.get(i);
            BtcMarketCapDtoWrapper btcMarketCapDto = btcMarketCapDtoList.get(i);
            AggViewDto t = AggViewDto.from(fearIndexDto, bitCoinPrice, eosCoinPrice, btcMarketCapDto);
            data.add(t);
        }

        return ApiResponseVO.success(data);
    }
}
