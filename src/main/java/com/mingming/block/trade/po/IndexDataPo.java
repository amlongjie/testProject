package com.mingming.block.trade.po;

import lombok.Data;

@Data
public class IndexDataPo {
    private Integer id;
    private Integer index; // 指数值
    private String date; // 日期
    private String type; // 指数类型
}