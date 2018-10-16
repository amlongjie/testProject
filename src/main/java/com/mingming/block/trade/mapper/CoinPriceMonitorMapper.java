package com.mingming.block.trade.mapper;

import com.mingming.block.trade.common.mybatis.SimpleSelectInExtendedLanguageDriver;
import com.mingming.block.trade.po.CoinPriceMonitorPo;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CoinPriceMonitorMapper {

    @Select("select * from coin_price_monitor where symbol = #{symbol} order by id desc limit 1")
    CoinPriceMonitorPo selectBySymbol(@Param(value = "symbol") String symbol);


    @Select("select * from coin_price_monitor order by id asc")
    List<CoinPriceMonitorPo> selectAll();

    @Lang(SimpleSelectInExtendedLanguageDriver.class)
    @Select("update coin_price_monitor set send = send ^ 1 where symbol in (#{symbolList})")
    void updateSendStatusBySymbolList(@Param(value = "symbolList") List<String> symbolList);
}
