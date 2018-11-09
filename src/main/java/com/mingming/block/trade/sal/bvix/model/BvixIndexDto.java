package com.mingming.block.trade.sal.bvix.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class BvixIndexDto {

    private List<BvixIndexData> points;


    @JsonDeserialize(using = BvixIndexDataDeserialize.class)
    public void setPoints(List<BvixIndexData> points) {
        this.points = points;
    }

    @ToString
    @Data
    public static class BvixIndexData {
        private String date;
        private int index;
    }
}
