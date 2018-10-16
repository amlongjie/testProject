package com.mingming.block.trade.dao;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.dto.CoinPriceMonitorDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.mapper.CoinPriceMonitorMapper;
import com.mingming.block.trade.po.CoinPriceMonitorPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CoinPriceMonitorDao {

    private final CoinPriceMonitorMapper coinPriceMonitorMapper;

    @Autowired
    public CoinPriceMonitorDao(CoinPriceMonitorMapper coinPriceMonitorMapper) {
        this.coinPriceMonitorMapper = coinPriceMonitorMapper;
    }

    public CoinPriceMonitorDto selectBySymbol(CoinEnum coinEnum) {
        Preconditions.checkArgument(coinEnum != null, "coinEnum is not nullable");
        CoinPriceMonitorPo coinPriceMonitorPo = coinPriceMonitorMapper.selectBySymbol(coinEnum.getSymbol());
        Preconditions.checkArgument(coinPriceMonitorPo != null, String.format("can not find monitor by symbol, symbol:%s", coinEnum.getSymbol()));
        return CoinPriceMonitorDto.from(coinPriceMonitorPo);
    }

    public List<CoinPriceMonitorDto> selectAll() {
        List<CoinPriceMonitorPo> coinPriceMonitorPoList = coinPriceMonitorMapper.selectAll();
        return coinPriceMonitorPoList.stream()
                .map(CoinPriceMonitorDto::from)
                .collect(Collectors.toList());

    }

    public int updateSendStatusBySymbolList(List<String> symbolList) {
        coinPriceMonitorMapper.updateSendStatusBySymbolList(symbolList);
        return symbolList.size();
    }
}
