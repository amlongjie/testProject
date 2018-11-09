package com.mingming.block.trade.sal.bvix.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.collect.Lists;
import com.mingming.block.trade.utils.DateUtils;

import java.io.IOException;
import java.util.List;

public class BvixIndexDataDeserialize extends JsonDeserializer<List<BvixIndexDto.BvixIndexData>> {
    @Override
    public List<BvixIndexDto.BvixIndexData> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        ArrayNode arrayNode = (ArrayNode) treeNode;
        List<BvixIndexDto.BvixIndexData> result = Lists.newArrayList();
        for (JsonNode node : arrayNode) {
            JsonNode timeStamp = node.get(0);
            JsonNode index = node.get(1);
            BvixIndexDto.BvixIndexData data = new BvixIndexDto.BvixIndexData();
            data.setDate(DateUtils.parseTimeStampToStr(timeStamp.longValue(), "yyyy-MM-dd"));
            int i = (int) (index.asDouble() * 100);
            data.setIndex(i);
            result.add(data);
        }
        return result;
    }
}
