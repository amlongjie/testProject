package com.mingming.block.trade.service.index.impl;

import com.mingming.block.trade.dto.IndexDataDto;
import com.mingming.block.trade.enums.IndexEnums;
import com.mingming.block.trade.sal.bvix.model.BvixIndexDto;
import com.mingming.block.trade.sal.dpc.DpcIndexSal;
import com.mingming.block.trade.sal.dpc.model.DpcIndexDto;
import com.mingming.block.trade.service.index.IIndexDataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DpcIndexFetchService implements IIndexDataFetchService {

    private final DpcIndexSal dpcIndexSal;

    @Autowired
    public DpcIndexFetchService(DpcIndexSal dpcIndexSal) {
        this.dpcIndexSal = dpcIndexSal;
    }

    @PostConstruct
    public void init() {
        IndexDataFetchServiceProxy.inject(IndexEnums.Dpc, this);
    }

    @Override
    public IndexDataDto fetchByEnums(IndexEnums indexEnums) {
        DpcIndexDto dpcIndexDto = dpcIndexSal.doGet();
        BvixIndexDto.BvixIndexData data = dpcIndexDto.getPoints().get(dpcIndexDto.getPoints().size() - 1);
        return new IndexDataDto(data.getIndex(), data.getDate(), indexEnums.getSymbol());

    }
}