package com.mingming.block.trade.converter;

import com.mingming.block.trade.dto.IndexDataDto;
import com.mingming.block.trade.po.IndexDataPo;
import org.springframework.stereotype.Component;

@Component
public class IndexDataConverter implements IConverter<IndexDataDto, IndexDataPo> {

    @Override
    public IndexDataPo doForward(IndexDataDto indexDataDto) {
        IndexDataPo indexDataPo = new IndexDataPo();
        indexDataPo.setId(null);
        indexDataPo.setIndex(indexDataDto.getIndex());
        indexDataPo.setDate(indexDataDto.getDate());
        indexDataPo.setType(indexDataDto.getType());
        return indexDataPo;
    }

    @Override
    public IndexDataDto doBackward(IndexDataPo indexDataPo) {
        IndexDataDto indexDataDto = new IndexDataDto();
        indexDataDto.setIndex(indexDataPo.getIndex());
        indexDataDto.setDate(indexDataPo.getDate());
        indexDataDto.setType(indexDataPo.getType());
        return indexDataDto;
    }
}
