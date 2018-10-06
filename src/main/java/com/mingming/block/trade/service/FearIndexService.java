package com.mingming.block.trade.service;

import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dao.FearIndexDao;
import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.sal.FearIndexSal;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class FearIndexService {

    private final FearIndexSal fearIndexSal;

    private final FearIndexDao fearIndexDao;

    private static final DateTimeFormatter crawlerFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

    @Autowired
    public FearIndexService(FearIndexSal fearIndexSal, FearIndexDao fearIndexDao) {
        this.fearIndexSal = fearIndexSal;
        this.fearIndexDao = fearIndexDao;
    }

    @ExHandlerAnnotation
    public ApiResponseDto<FearIndexDto> crawl() {
        FearIndexDto fearIndexDto = fetchFearGreedIndex();
        return ApiResponseDto.success(fearIndexDto);
    }

    @ExHandlerAnnotation
    public ApiResponseDto<Integer> store() {
        FearIndexDto popDto = fearIndexDao.selectPop();
        if (popDto != null && LocalDate.now().equals(popDto.getDate())) {
            log.info(String.format("pop Data is today's data. %s", popDto));
            return ApiResponseDto.success(0);
        }
        FearIndexDto fearIndexDto = fetchFearGreedIndex();
        Integer affect = fearIndexDao.insert(fearIndexDto);
        return ApiResponseDto.success(affect);
    }


    @ExHandlerAnnotation
    public ApiResponseDto<List<FearIndexDto>> search() {
        List<FearIndexDto> fearIndexDtoList = fearIndexDao.selectAll();
        return ApiResponseDto.success(fearIndexDtoList);
    }


    private FearIndexDto fetchFearGreedIndex() {
        // 抓取数据
        Document doc = fearIndexSal.doGet();

        // 获取指数
        Elements fearIndexElements = doc.getElementsByClass("fng-circle");
        Element element = fearIndexElements.get(0);
        String index = element.text();

        // 获取说明
        Elements statusElements = doc.getElementsByClass("status");
        Element statusElement = statusElements.get(0);
        String status = statusElement.text();

        Elements updateTimeElements = doc.getElementsByClass("fng-footer");
        Element updateTimeElement = updateTimeElements.get(0);
        LocalDate date = LocalDate.parse(updateTimeElement.text().replace("Last updated: ", ""), crawlerFormatter);
        // 构造结果
        return new FearIndexDto(date, Integer.valueOf(index), status);
    }


}
