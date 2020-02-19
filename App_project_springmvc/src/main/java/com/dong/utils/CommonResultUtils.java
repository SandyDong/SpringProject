package com.dong.utils;

import com.dong.common.Result;

/**
 * 统一结果返回格式处理工具类(静态方法需要返回一个确定的类型)
 */
public class CommonResultUtils {

    private CommonResultUtils(){

    }

    /**
     * 执行成功时调用的方法,需要返回数据
     * @param object
     * @return
     */
    public  static  Result   success(Object object){
        Result result = new Result();
        result.setCode("200");
        result.setMessage("调用接口成功!");
        result.setData(object);
        return  result;
    }
    /**
     * 执行成功时调用的方法,不需要返回数据
     * @return
     */
    public static Result success(){
        return success(null);
    }

    /**
     *  执行失败时调用的方法,需要传入失败码和错误信息
     * @param errCode
     * @param message
     * @return
     */
    public  static  Result   error(String errCode,String message){
        Result result = new Result();
        result.setCode(errCode);
        result.setMessage(message);
        result.setData(null);
        return  result;
    }
}
