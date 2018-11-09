package com.mingming.block.trade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexDataDto {

    private Integer index; // 指数值
    private String date; // 日期
    private String type; // 指数类型

}
