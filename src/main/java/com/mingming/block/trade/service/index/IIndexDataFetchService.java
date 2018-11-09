package com.mingming.block.trade.service.index;

import com.mingming.block.trade.dto.IndexDataDto;
import com.mingming.block.trade.enums.IndexEnums;

public interface IIndexDataFetchService {

    IndexDataDto fetchByEnums(IndexEnums indexEnums);
}
