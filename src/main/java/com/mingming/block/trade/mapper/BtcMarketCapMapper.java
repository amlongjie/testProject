package com.mingming.block.trade.mapper;

import com.mingming.block.trade.po.BtcMarketCapPo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BtcMarketCapMapper {

    @Insert("insert into btc_market_cap(`percent`,`date`) values(#{po.percent},  #{po.date})")
    @SelectKey(keyColumn = "id",
            keyProperty = "po.id",
            before = false,
            statement = "SELECT LAST_INSERT_ID()",
            resultType = Integer.class)
    Integer insert(@Param(value = "po") BtcMarketCapPo btcMarketCapPo);


    @Select("select * from btc_market_cap order by id desc limit 1")
    BtcMarketCapPo selectPop();

    @Select("select * from btc_market_cap order by id asc")
    List<BtcMarketCapPo> selectAll();
}
