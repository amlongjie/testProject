package com.mingming.block.trade.controller.page;

import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.CoinPriceDto;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.enums.CoinEnum;
import com.mingming.block.trade.service.CoinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/coin")
public class CoinPricePageController {

    private final CoinPriceService coinPriceService;

    @Autowired
    public CoinPricePageController(CoinPriceService coinPriceService) {
        this.coinPriceService = coinPriceService;
    }

    @GetMapping("/search")
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();

        ApiResponseDto<List<CoinPriceDto>> btcResponseDto = coinPriceService.search(CoinEnum.Eos.getSymbol());
        if (btcResponseDto.getCode() == 1) {
            modelAndView.setViewName("error_page");
            modelAndView.addObject("data", btcResponseDto.getMsg());
            return modelAndView;
        }
        modelAndView.setViewName("coin/search");
        List<CoinPriceDto> data = btcResponseDto.getData();
        modelAndView.addObject("data", data);
        return modelAndView;
    }
}
