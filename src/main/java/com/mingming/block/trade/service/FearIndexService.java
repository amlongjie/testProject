package com.mingming.block.trade.service;

import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dao.CoinPriceDao;
import com.mingming.block.trade.dao.FearIndexDao;
import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.FearGreedIndexDto;
import com.mingming.block.trade.sal.FearGreedIndexSal;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
    public ApiResponseDto<FearGreedIndexDto> crawl() {
        FearGreedIndexDto fearGreedIndexDto = fetchFearGreedIndex();
        return ApiResponseDto.success(fearGreedIndexDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<FearGreedIndexDto> store() {
        FearGreedIndexDto fearGreedIndexDto = fetchFearGreedIndex();
        fearIndexDao.insert(fearGreedIndexDto);
        return ApiResponseDto.success(fearGreedIndexDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<FearGreedIndexDto> pop() {
        FearGreedIndexDto fearGreedIndexDto = fearIndexDao.selectPop();
        return ApiResponseDto.success(fearGreedIndexDto);
    }

    private FearGreedIndexDto fetchFearGreedIndex() {
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
        return new FearGreedIndexDto(LocalDate.now(), Integer.valueOf(index), status);
    }
}
