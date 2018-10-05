package com.mingming.block.trade.controller.page;

import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.FearIndexWithCoinPriceDto;
import com.mingming.block.trade.facade.FearIndexWithCoinPriceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/agg")
public class FearIndexWithCoinPricePageController {

    private final FearIndexWithCoinPriceFacade fearIndexWithCoinPriceFacade;

    @Autowired
    public FearIndexWithCoinPricePageController(FearIndexWithCoinPriceFacade fearIndexWithCoinPriceFacade) {
        this.fearIndexWithCoinPriceFacade = fearIndexWithCoinPriceFacade;
    }


    @RequestMapping("/iwp")
    public ModelAndView fearIndexWithCoinPriceView() {
        ModelAndView modelAndView = new ModelAndView();

        ApiResponseDto<List<FearIndexWithCoinPriceDto>> responseDto = fearIndexWithCoinPriceFacade.queryFearIndexWithCoinPrice();

        if (responseDto.getCode() == 1) {
            modelAndView.setViewName("error_page");
            modelAndView.addObject("data", responseDto.getMsg());
            return modelAndView;
        }
        modelAndView.setViewName("agg/iwp");
        List<FearIndexWithCoinPriceDto> data = responseDto.getData();
        modelAndView.addObject("data", data);

        return modelAndView;
    }
}
