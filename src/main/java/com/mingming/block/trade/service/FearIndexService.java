package com.mingming.block.trade.service;

import com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation;
import com.mingming.block.trade.dao.FearIndexDao;
import com.mingming.block.trade.dto.ApiResponseVO;
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
    public ApiResponseVO<FearIndexDto> crawl() {
        FearIndexDto fearIndexDto = fetchFearGreedIndex();
        return ApiResponseVO.success(fearIndexDto);
    }

    @ExHandlerAnnotation
    public ApiResponseVO<Integer> store() {
        FearIndexDto popDto = fearIndexDao.selectPop();
        // 如果是Null,直接设值
        if (popDto == null) {
            FearIndexDto fearIndexDto = fetchFearGreedIndex();
            Integer affect = fearIndexDao.insert(fearIndexDto);
            return ApiResponseVO.success(affect);
        }

        LocalDate today = LocalDate.now();
        // 今天已经添加过
        if (today.isEqual(popDto.getDate())) {
            log.info(String.format("pop Data is today's data. %s", popDto));
            return ApiResponseVO.success(0);
        }

        FearIndexDto fearIndexDto = fetchFearGreedIndex();
        // 恐慌指数已经更新
        if (today.isEqual(fearIndexDto.getDate())) {
            Integer affect = fearIndexDao.insert(fearIndexDto);
            return ApiResponseVO.success(affect);
        }

        return ApiResponseVO.success(0);
    }


    @ExHandlerAnnotation
    public ApiResponseVO<List<FearIndexDto>> search() {
        List<FearIndexDto> fearIndexDtoList = fearIndexDao.selectAll();
        return ApiResponseVO.success(fearIndexDtoList);
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
