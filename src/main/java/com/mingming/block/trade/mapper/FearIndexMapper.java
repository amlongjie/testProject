package com.mingming.block.trade.mapper;

import com.mingming.block.trade.po.FearIndexPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FearIndexMapper {

    @Insert("insert into fear_index(`index`, `status`, `date`) values(#{po.index}, #{po.status}, #{po.date})")
    @SelectKey(keyColumn = "id",
            keyProperty = "po.id",
            before = false,
            statement = "SELECT LAST_INSERT_ID()",
            resultType = Integer.class)
    Integer insert(@Param(value = "po") FearIndexPo fearIndexPo);


    @Select("select * from fear_index order by id desc limit 1")
    FearIndexPo selectPop();

    @Select("select * from fear_index order by id asc")
    List<FearIndexPo> selectAll();

}
