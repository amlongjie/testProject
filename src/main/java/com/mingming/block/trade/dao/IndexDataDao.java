package com.mingming.block.trade.dao;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.dto.IndexDataDto;
import com.mingming.block.trade.mapper.FearIndexMapper;
import com.mingming.block.trade.mapper.IndexDataMapper;
import com.mingming.block.trade.po.FearIndexPo;
import com.mingming.block.trade.po.IndexDataPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IndexDataDao {

    private final IndexDataMapper indexDataMapper;

    @Autowired
    public IndexDataDao(IndexDataMapper indexDataMapper) {
        this.indexDataMapper = indexDataMapper;
    }

    /**
     * 添加一条
     *
     * @param indexDataPo
     * @return
     */
    public Integer insert(IndexDataPo indexDataPo) {
        Integer affect = indexDataMapper.insert(indexDataPo);
        Preconditions.checkArgument(affect != null && affect > 0, "insert execute failed");
        return affect;
    }


    /**
     * 查询所有
     *
     * @return
     */
    public List<IndexDataPo> selectAllBySymbol(String symbol) {
        return indexDataMapper.selectAllBySymbol(symbol);
    }

}
