package com.mingming.block.trade.service.index.impl;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.dto.IndexDataDto;
import com.mingming.block.trade.enums.IndexEnums;
import com.mingming.block.trade.sal.FearIndexSal;
import com.mingming.block.trade.service.index.IIndexDataFetchService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FearIndexFetchService implements IIndexDataFetchService {

    private final FearIndexSal fearIndexSal;

    @Autowired
    public FearIndexFetchService(FearIndexSal fearIndexSal) {
        this.fearIndexSal = fearIndexSal;
    }

    @PostConstruct
    public void init() {
        IndexDataFetchServiceProxy.inject(IndexEnums.Fear, this);
    }

    @Override
    public IndexDataDto fetchByEnums(IndexEnums indexEnums) {
        // 抓取数据
        Document doc = fearIndexSal.doGet();

        // 获取指数
        Elements fearIndexElements = doc.getElementsByClass("fng-circle");
        Element element = fearIndexElements.get(0);
        String index = element.text();

        Elements updateTimeElements = doc.getElementsByClass("fng-footer");
        Element updateTimeElement = updateTimeElements.get(0);
        String date = parseDate(updateTimeElement.text().replace("Last updated: ", ""));
        // 构造结果
        return new IndexDataDto(Integer.valueOf(index) * 100, date, IndexEnums.Fear.getSymbol());
    }

    /**
     * November 07, 2018 => 20181-11-07
     *
     * @param originDate
     * @return
     */
    private String parseDate(String originDate) {
        Preconditions.checkArgument(StringUtils.isNotBlank(originDate), String.format("日期为空:%s", originDate));
        StringBuilder result = new StringBuilder();

        String[] split = originDate.split(" ");
        List<String> dateList = Arrays.stream(split)
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .map(item -> StringUtils.strip(item, ","))
                .collect(Collectors.toList());

        String year = dateList.get(2);
        result.append(year).append("-");
        String month = fetchMonth(dateList.get(0));
        result.append(month).append("-");
        String day = dateList.get(1);
        result.append(day);
        return result.toString();
    }

    private String fetchMonth(String s) {
        switch (s) {
            case "January":
                return "1";
            case "February":
                return "2";
            case "March":
                return "3";
            case "April":
                return "4";
            case "May":
                return "5";
            case "June":
                return "6";
            case "July":
                return "7";
            case "August":
                return "8";
            case "September":
                return "9";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
        }
        return "-1";
    }

}
