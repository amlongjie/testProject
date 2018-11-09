package com.mingming.block.trade.service.index.impl;

import com.mingming.block.trade.dto.IndexDataDto;
import com.mingming.block.trade.enums.IndexEnums;
import com.mingming.block.trade.sal.bvix.BvixIndexSal;
import com.mingming.block.trade.sal.bvix.model.BvixIndexDto;
import com.mingming.block.trade.service.index.IIndexDataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BvixIndexFetchService implements IIndexDataFetchService {

    private final BvixIndexSal bvixIndexSal;

    @Autowired
    public BvixIndexFetchService(BvixIndexSal bvixIndexSal) {
        this.bvixIndexSal = bvixIndexSal;
    }

    @PostConstruct
    public void init() {
        IndexDataFetchServiceProxy.inject(IndexEnums.Bvix, this);
    }

    @Override
    public IndexDataDto fetchByEnums(IndexEnums indexEnums) {
        BvixIndexDto bvixIndexDto = bvixIndexSal.doGet();
        BvixIndexDto.BvixIndexData data = bvixIndexDto.getPoints().get(bvixIndexDto.getPoints().size() - 1);
        return new IndexDataDto(data.getIndex(), data.getDate(), indexEnums.getSymbol());

    }


}
