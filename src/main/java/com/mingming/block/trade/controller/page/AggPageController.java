package com.mingming.block.trade.controller.page;

import com.mingming.block.trade.dto.ApiResponseVO;
import com.mingming.block.trade.dto.AggViewDto;
import com.mingming.block.trade.facade.FearIndexWithCoinPriceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/agg")
public class AggPageController {

    private final FearIndexWithCoinPriceFacade fearIndexWithCoinPriceFacade;

    @Autowired
    public AggPageController(FearIndexWithCoinPriceFacade fearIndexWithCoinPriceFacade) {
        this.fearIndexWithCoinPriceFacade = fearIndexWithCoinPriceFacade;
    }


    @RequestMapping("/iwp")
    public ModelAndView aggView() {
        ModelAndView modelAndView = new ModelAndView();

        ApiResponseVO<List<AggViewDto>> responseDto = fearIndexWithCoinPriceFacade.queryFearIndexWithCoinPrice();

        if (responseDto.getCode() == 1) {
            modelAndView.setViewName("error_page");
            modelAndView.addObject("data", responseDto.getMsg());
            return modelAndView;
        }
        modelAndView.setViewName("agg/iwp");
        List<AggViewDto> data = responseDto.getData();
        modelAndView.addObject("data", data);

        return modelAndView;
    }

    @RequestMapping("/iwp2")
    public ModelAndView aggView2() {
        ModelAndView modelAndView = new ModelAndView();

        ApiResponseVO<List<AggViewDto>> responseDto = fearIndexWithCoinPriceFacade.queryFearIndexWithCoinPrice();

        if (responseDto.getCode() == 1) {
            modelAndView.setViewName("error_page");
            modelAndView.addObject("data", responseDto.getMsg());
            return modelAndView;
        }
        modelAndView.setViewName("agg/iwp2");
        List<AggViewDto> data = responseDto.getData();
        modelAndView.addObject("data", data);

        return modelAndView;
    }
}
