package com.mingming.block.trade.aspect;

import com.mingming.block.trade.dto.ApiResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class GlobalServiceExceptionAspect {

    @Around(value = "@annotation(com.mingming.block.trade.aspect.annotation.ExHandlerAnnotation)")
    public ApiResponseVO apiResponseDto(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object proceed = joinPoint.proceed();
            return (ApiResponseVO) proceed;
        } catch (Exception e) {
            log.error("", e);
            return ApiResponseVO.fail(e);
        }
    }
}
