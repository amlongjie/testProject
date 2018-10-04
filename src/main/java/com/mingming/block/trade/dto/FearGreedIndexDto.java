package com.mingming.block.trade.dto;

import com.google.common.base.Preconditions;
import com.mingming.block.trade.po.FearIndexPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FearGreedIndexDto {
    private LocalDate date;
    private Integer index;
    private String status;

    public static FearIndexPo to(FearGreedIndexDto fearGreedIndexDto) {
        Preconditions.checkArgument(fearGreedIndexDto != null, "fearGreedIndexDto is null");
        FearIndexPo fearIndexPo = new FearIndexPo();
        fearIndexPo.setDate(fearGreedIndexDto.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        fearIndexPo.setIndex(fearGreedIndexDto.getIndex());
        fearIndexPo.setStatus(fearGreedIndexDto.getStatus());
        return fearIndexPo;
    }

    public static FearGreedIndexDto from(FearIndexPo fearIndexPo) {
        Preconditions.checkArgument(fearIndexPo != null, "fearIndexPo is null");
        FearGreedIndexDto fearGreedIndexDto = new FearGreedIndexDto();
        fearGreedIndexDto.setDate(LocalDate.parse(fearIndexPo.getDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        fearGreedIndexDto.setIndex(fearIndexPo.getIndex());
        fearGreedIndexDto.setStatus(fearIndexPo.getStatus());
        return fearGreedIndexDto;
    }
}
