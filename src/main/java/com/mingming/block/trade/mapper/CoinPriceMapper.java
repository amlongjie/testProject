package com.mingming.block.trade.mapper;

import com.mingming.block.trade.po.CoinPricePo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CoinPriceMapper {

    @Insert("insert into coin_price(`price`, `symbol`, `date`) values(#{po.price}, #{po.symbol}, #{po.date})" +
            "ON DUPLICATE KEY UPDATE `price` = #{po.price}")
    @SelectKey(keyColumn = "id",
            keyProperty = "po.id",
            before = false,
            statement = "SELECT LAST_INSERT_ID()",
            resultType = Integer.class)
    Integer insert(@Param(value = "po") CoinPricePo coinPricePo);


    @Select("select * from coin_price where symbol = #{symbol} order by id desc limit 1")
    CoinPricePo pop(@Param(value = "symbol") String symbol);

    @Select("select * from coin_price where symbol = #{symbol} order by id asc")
    List<CoinPricePo> selectAllBySymbol(@Param(value = "symbol") String symbol);
}
