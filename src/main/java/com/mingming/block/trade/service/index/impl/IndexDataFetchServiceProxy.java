package com.mingming.block.trade.service.index.impl;

import com.google.common.collect.Maps;
import com.mingming.block.trade.dto.IndexDataDto;
import com.mingming.block.trade.enums.IndexEnums;
import com.mingming.block.trade.service.index.IIndexDataFetchService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IndexDataFetchServiceProxy implements IIndexDataFetchService {

    private static Map<IndexEnums, IIndexDataFetchService> serviceMap = Maps.newHashMap();

    static void inject(IndexEnums enums, IIndexDataFetchService fetchService) {
        serviceMap.put(enums, fetchService);
    }

    @Override
    public IndexDataDto fetchByEnums(IndexEnums indexEnums) {
        return serviceMap.get(indexEnums).fetchByEnums(indexEnums);
    }
}
