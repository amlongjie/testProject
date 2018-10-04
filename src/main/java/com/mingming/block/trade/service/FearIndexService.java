package com.mingming.block.trade.service;

import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dao.FearIndexDao;
import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.sal.FearGreedIndexSal;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class FearIndexService {

    private final FearGreedIndexSal fearGreedIndexSal;

    private final FearIndexDao fearIndexDao;

    @Autowired
    public FearIndexService(FearGreedIndexSal fearGreedIndexSal, FearIndexDao fearIndexDao) {
        this.fearGreedIndexSal = fearGreedIndexSal;
        this.fearIndexDao = fearIndexDao;
    }

    @ExHandlerAnnotation
    public ApiResponseDto<FearIndexDto> crawl() {
        FearIndexDto fearIndexDto = fetchFearGreedIndex();
        return ApiResponseDto.success(fearIndexDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<FearIndexDto> store() {
        FearIndexDto fearIndexDto = fetchFearGreedIndex();
        fearIndexDao.insert(fearIndexDto);
        return ApiResponseDto.success(fearIndexDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<FearIndexDto> pop() {
        FearIndexDto fearIndexDto = fearIndexDao.selectPop();
        return ApiResponseDto.success(fearIndexDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<List<FearIndexDto>> search() {
        List<FearIndexDto> fearIndexDtoList = fearIndexDao.selectAll();
        return ApiResponseDto.success(fearIndexDtoList);
    }


    private FearIndexDto fetchFearGreedIndex() {
        // 抓取数据
        Document doc = fearGreedIndexSal.doGet();

        // 获取指数
        Elements fearIndexElements = doc.getElementsByClass("fng-circle");
        Element element = fearIndexElements.get(0);
        String index = element.text();

        // 获取说明
        Elements statusElements = doc.getElementsByClass("status");
        Element statusElement = statusElements.get(0);
        String status = statusElement.text();

        // 构造结果
        return new FearIndexDto(LocalDate.now(), Integer.valueOf(index), status);
    }


}
