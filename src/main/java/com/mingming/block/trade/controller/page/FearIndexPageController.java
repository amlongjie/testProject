package com.mingming.block.trade.controller.page;

import com.mingming.block.trade.dto.ApiResponseDto;
import com.mingming.block.trade.dto.FearIndexDto;
import com.mingming.block.trade.service.FearIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/fear")
public class FearIndexPageController {

    private final FearIndexService fearIndexService;

    @Autowired
    public FearIndexPageController(FearIndexService fearIndexService) {
        this.fearIndexService = fearIndexService;
    }

    @GetMapping("/search")
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();

        ApiResponseDto<List<FearIndexDto>> responseDto = fearIndexService.search();
        if (responseDto.getCode() == 1) {
            modelAndView.setViewName("error_page");
            modelAndView.addObject("data", responseDto.getMsg());
            return modelAndView;
        }
        modelAndView.setViewName("fear/search");
        List<FearIndexDto> data = responseDto.getData();
        modelAndView.addObject("data", data);
        return modelAndView;
    }
}
