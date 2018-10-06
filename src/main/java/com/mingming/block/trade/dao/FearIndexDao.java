package com.mingming.block.trade.dao;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.mapper.FearIndexMapper;
import com.mingming.block.trade.po.FearIndexPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param fearIndexDto
     * @return
     */
    public Integer insert(FearIndexDto fearIndexDto) {
        FearIndexPo fearIndexPo = FearIndexDto.to(fearIndexDto);
        Integer affect = fearIndexMapper.insert(fearIndexPo);
        Preconditions.checkArgument(affect != null && affect == 1, "insert execute failed");
        return affect;
    }

    /**
     * 查最近一条
     *
     * @return
     */
    @Nullable
    public FearIndexDto selectPop() {
        FearIndexPo fearIndexPo = fearIndexMapper.selectPop();
        if (fearIndexPo == null) {
            return null;
        }
        return FearIndexDto.from(fearIndexPo);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<FearIndexDto> selectAll() {
        List<FearIndexPo> fearIndexPoList = fearIndexMapper.selectAll();
        return fearIndexPoList.stream()
                .map(FearIndexDto::from)
                .collect(Collectors.toList());
    }
}
