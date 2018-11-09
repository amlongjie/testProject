package com.mingming.block.trade.controller.rest;

import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.IndexDataDto;
import com.mingming.block.trade.service.IndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexDataRestController {

    private final IndexDataService indexDataService;

    @Autowired
    public IndexDataRestController(IndexDataService indexDataService) {
        this.indexDataService = indexDataService;
    }

    @GetMapping("/get/{symbol}")
    public ApiResponseVO<IndexDataDto> fetchTicker(@PathVariable(name = "symbol") String symbol) {
        return indexDataService.crawl(symbol);
    }
}
