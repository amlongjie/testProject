package com.mingming.block.trade.dao;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.dto.FearGreedIndexDto;
import com.mingming.block.trade.mapper.FearIndexMapper;
import com.mingming.block.trade.po.FearIndexPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;

@Repository
public class FearIndexDao {

    private final FearIndexMapper fearIndexMapper;

    @Autowired
    public FearIndexDao(FearIndexMapper fearIndexMapper) {
        this.fearIndexMapper = fearIndexMapper;
    }

    /**
     * 添加一条
     *
     * @param fearGreedIndexDto
     * @return
     */
    public Integer insert(FearGreedIndexDto fearGreedIndexDto) {
        FearIndexPo fearIndexPo = FearGreedIndexDto.to(fearGreedIndexDto);
        Integer affect = fearIndexMapper.insert(fearIndexPo);
        Preconditions.checkArgument(affect != null && affect == 1, "insert execute failed");
        return affect;
    }

    /**
     * 查最近一条
     *
     * @return
     */
    public FearGreedIndexDto selectPop() {
        FearIndexPo fearIndexPo = fearIndexMapper.selectPop();
        Preconditions.checkArgument(fearIndexPo != null, "pop result is null");
        return FearGreedIndexDto.from(fearIndexPo);
    }
}
