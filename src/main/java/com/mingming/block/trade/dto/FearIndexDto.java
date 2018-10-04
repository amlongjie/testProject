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
public class FearIndexDto {
    private LocalDate date;
    private Integer index;
    private String status;

    public static FearIndexPo to(FearIndexDto fearIndexDto) {
        Preconditions.checkArgument(fearIndexDto != null, "fearIndexDto is null");
        FearIndexPo fearIndexPo = new FearIndexPo();
        fearIndexPo.setDate(fearIndexDto.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        fearIndexPo.setIndex(fearIndexDto.getIndex());
        fearIndexPo.setStatus(fearIndexDto.getStatus());
        return fearIndexPo;
    }

    public static FearIndexDto from(FearIndexPo fearIndexPo) {
        Preconditions.checkArgument(fearIndexPo != null, "fearIndexPo is null");
        FearIndexDto fearIndexDto = new FearIndexDto();
        fearIndexDto.setDate(LocalDate.parse(fearIndexPo.getDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        fearIndexDto.setIndex(fearIndexPo.getIndex());
        fearIndexDto.setStatus(fearIndexPo.getStatus());
        return fearIndexDto;
    }
}
