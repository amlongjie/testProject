package com.mingming.block.trade.dao;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.dto.BtcMarketCapDtoWrapper;
import com.mingming.block.trade.mapper.BtcMarketCapMapper;
import com.mingming.block.trade.po.BtcMarketCapPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BtcMarketCapDao {

    private final BtcMarketCapMapper btcMarketCapMapper;

    @Autowired
    public BtcMarketCapDao(BtcMarketCapMapper btcMarketCapMapper) {
        this.btcMarketCapMapper = btcMarketCapMapper;
    }

    /**
     * 添加一条
     *
     * @param btcMarketCapDto
     * @return
     */
    public int insert(BtcMarketCapDtoWrapper btcMarketCapDto) {
        BtcMarketCapPo po = BtcMarketCapDtoWrapper.to(btcMarketCapDto);
        Integer affect = btcMarketCapMapper.insert(po);
        Preconditions.checkArgument(affect != null && affect > 0, "insert execute failed");
        return affect;
    }

    /**
     * 查询最近一条
     *
     * @return
     */
    @Nullable
    public BtcMarketCapDtoWrapper selectPop() {
        BtcMarketCapPo po = btcMarketCapMapper.selectPop();
        if (po == null) {
            return null;
        }
        return BtcMarketCapDtoWrapper.from(po);
    }

    /**
     * @return
     */
    public List<BtcMarketCapDtoWrapper> selectAll() {
        List<BtcMarketCapPo> btcMarketCapPoList = btcMarketCapMapper.selectAll();
        return btcMarketCapPoList.stream()
                .map(BtcMarketCapDtoWrapper::from)
                .collect(Collectors.toList());
    }
}
