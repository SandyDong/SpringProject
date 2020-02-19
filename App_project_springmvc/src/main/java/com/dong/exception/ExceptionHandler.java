package com.dong.exception;

import com.dong.common.Result;
import com.dong.utils.CommonResultUtils;
import org.junit.internal.ExactComparisonCriteria;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常信息捕获类
 *
 * 一：ControllerAdvice 注解有如下几个场景可以使用
 * 全局异常处理
 * 全局数据绑定
 * 全局数据预处理
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof PriceException){
            PriceException priceException  = (PriceException) e;
            return CommonResultUtils.error(priceException.getErrCode(),priceException.getMessage());
        }else {
            return CommonResultUtils.error("-1",e.getMessage());
        }
    }

}
