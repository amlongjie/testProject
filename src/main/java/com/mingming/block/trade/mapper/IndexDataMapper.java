package com.mingming.block.trade.mapper;

import com.mingming.block.trade.po.IndexDataPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndexDataMapper {

    @Insert("insert into index_data(`index`, `date`, `type`) values(#{po.index}, #{po.date}, #{po.type})" +
            " ON DUPLICATE KEY UPDATE `index` = #{po.date}, `status` = #{po.type}")
    @SelectKey(keyColumn = "id",
            keyProperty = "po.id",
            before = false,
            statement = "SELECT LAST_INSERT_ID()",
            resultType = Integer.class)
    Integer insert(@Param(value = "po") IndexDataPo indexDataPo);

    @Select("select * from index_data where type = #{symbol} order by id asc")
    List<IndexDataPo> selectAllBySymbol(@Param("symbol") String symbol);
}
