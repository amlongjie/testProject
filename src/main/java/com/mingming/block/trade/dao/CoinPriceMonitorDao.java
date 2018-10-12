package com.mingming.block.trade.dao;

import com.mingming.block.trade.dto.CoinPriceMonitorDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.mapper.CoinPriceMonitorMapper;
import com.mingming.block.trade.po.CoinPriceMonitorPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CoinPriceMonitorDao {

    private final CoinPriceMonitorMapper coinPriceMonitorMapper;

    @Autowired
    public CoinPriceMonitorDao(CoinPriceMonitorMapper coinPriceMonitorMapper) {
        this.coinPriceMonitorMapper = coinPriceMonitorMapper;
    }

    public CoinPriceMonitorDto selectBySymbol(CoinEnum coinEnum) {
        CoinPriceMonitorPo coinPriceMonitorPo = coinPriceMonitorMapper.selectBySymbol(coinEnum.getSymbol());
        return CoinPriceMonitorDto.from(coinPriceMonitorPo);
    }
}
