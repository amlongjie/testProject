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
        FearIndexDto fearIndexDto = fetchFearGreedIndex();
        Integer affect = fearIndexDao.insert(fearIndexDto);
        return ApiResponseVO.success(affect);
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

    /*
    全量数据,2018-01-01开始
    "30","15","40","24","11","8","36","30","44","54","31","42","35","55","71",
    "67","74","63","67","74","54","44","39","31","33","37","44","41","38","47",
    "56","44","55","59","37","39","37","39","40","41","41","40","32","33","31",
    "29","29","37","36","36","28","32","30","31","24","24","18","12","16","16",
    "11","22","22","17","19","20","17","21","18","20","18","23","26","24","25",
    "26","32","31","28","29","64","47","55","54","61","59","56","52","55","56",
    "63","67","56","62","53","63","41","44","40","40","40","32","31","37","31",
    "32","41","30","26","27","25","23","19","22","16","38","25","24","27","40",
    "41","26","42","38","40","39","24","15","19","19","17","26","22","23","27",
    "32","34","37","28","17","15","16","21","18","20","16","22","27","27","31",
    "33","37","34","34","38","39","37","29","33","29","29","32","36","39","42",
    "44","47","43","46","44","49","54","53","47","54","54","53","48","39","39",
    "36","31","23","25","25","23","19","21","18","18","21","16","18","21","19",
    "24","27","26","19","21","18","19","22","19","18","19","19","22","17","21",
    "18","19","26","17","14","17","18","13","15","18","14","20","23","24","28",
    "25","21","24","24","31","35","38","43","37","37","42","42","37","34","35",
    "33","36","29","37","34","29","26","31","28","19"
     */

}
