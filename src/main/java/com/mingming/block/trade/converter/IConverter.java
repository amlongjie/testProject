package com.mingming.block.trade.converter;

public interface IConverter<F, T> {

    T doForward(F f);

    F doBackward(T t);
}
