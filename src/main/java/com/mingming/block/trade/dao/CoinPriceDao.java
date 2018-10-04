package com.mingming.block.trade.dao;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.dto.CoinPriceDto;
import com.mingming.block.trade.mapper.CoinPriceMapper;
import com.mingming.block.trade.po.CoinPricePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CoinPriceDao {

    private final CoinPriceMapper coinPriceMapper;

    @Autowired
    public CoinPriceDao(CoinPriceMapper coinPriceMapper) {
        this.coinPriceMapper = coinPriceMapper;
    }

    /**
     * 添加一条
     *
     * @param coinPriceDto
     * @return
     */
    public int insert(CoinPriceDto coinPriceDto) {
        CoinPricePo po = CoinPriceDto.to(coinPriceDto);
        Integer affect = coinPriceMapper.insert(po);
        Preconditions.checkArgument(affect != null && affect == 1, "insert execute failed");
        return affect;
    }

    /**
     * 查询最近一条
     *
     * @param symbol
     * @return
     */
    public CoinPriceDto selectPop(String symbol) {
        CoinPricePo po = coinPriceMapper.pop(symbol);
        return CoinPriceDto.from(po);
    }

    /**
     * 查询指定代币的价格
     *
     * @param symbol
     * @return
     */
    public List<CoinPriceDto> selectAll(String symbol) {
        List<CoinPricePo> coinPriceDtoList = coinPriceMapper.selectAllBySymbol(symbol);
        return coinPriceDtoList.stream()
                .map(CoinPriceDto::from)
                .collect(Collectors.toList());
    }
}
