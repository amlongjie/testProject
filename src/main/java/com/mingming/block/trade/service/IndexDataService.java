package com.mingming.block.trade.service;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.converter.IndexDataConverter;
import com.mingming.block.trade.dao.IndexDataDao;
import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.CoinPriceMonitorDto;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.dto.IndexDataDto;
import com.mingming.block.trade.enums.IndexEnums;
import com.mingming.block.trade.po.IndexDataPo;
import com.mingming.block.trade.service.index.IIndexDataFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexDataService {

    private final IIndexDataFetchService indexDataFetchService;

    private final IndexDataDao indexDataDao;

    private final IndexDataConverter indexDataConverter;

    @Autowired
    public IndexDataService(@Qualifier("indexDataFetchServiceProxy") IIndexDataFetchService indexDataFetchService, IndexDataDao indexDataDao, IndexDataConverter indexDataConverter) {
        this.indexDataFetchService = indexDataFetchService;
        this.indexDataDao = indexDataDao;
        this.indexDataConverter = indexDataConverter;
    }


    @ExHandlerAnnotation
    public ApiResponseVO<IndexDataDto> crawl(String symbol) {
        IndexDataDto indexDataDto = fetchIndexDataBySymbol(symbol);
        return ApiResponseVO.success(indexDataDto);
    }

    @ExHandlerAnnotation
    public ApiResponseVO<Integer> store(String symbol) {
        IndexDataDto indexDataDto = fetchIndexDataBySymbol(symbol);
        Integer affect = indexDataDao.insert(indexDataConverter.doForward(indexDataDto));
        return ApiResponseVO.success(affect);
    }

    @ExHandlerAnnotation
    public ApiResponseVO<List<IndexDataDto>> search(String symbol) {
        List<IndexDataPo> indexDataPos = indexDataDao.selectAllBySymbol(symbol);
        List<IndexDataDto> dataDtoList = indexDataPos.stream().map(indexDataConverter::doBackward).collect(Collectors.toList());
        return ApiResponseVO.success(dataDtoList);
    }

    private IndexDataDto fetchIndexDataBySymbol(String symbol) {
        IndexEnums indexEnums = IndexEnums.fetchBySymbol(symbol);
        Preconditions.checkArgument(indexEnums != null, "indexEnums is not nullable");
        IndexDataDto indexDataDto = indexDataFetchService.fetchByEnums(indexEnums);
        Preconditions.checkArgument(indexDataDto != null, "index is null");
        return indexDataDto;
    }


//
//
//    @ExHandlerAnnotation
//    public ApiResponseVO<List<IndexDataDto>> search() {
//        List<FearIndexDto> fearIndexDtoList = fearIndexDao.selectAll();
//        return ApiResponseVO.success(fearIndexDtoList);
//    }

}
