package com.dong.exception;

import com.dong.enumclass.ErrorCodeEnum;

/**
 * 注意自定义的异常类要继承RuntimeException
 * 因为Spring会对RuntimeException进行事务回滚
 * 不要继承exception类
 *
 */
public class PriceException extends RuntimeException{

    private String errCode;


    public PriceException(String errCode,String message) {
        super(message);
        this.errCode = errCode;
    }

    public PriceException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.errCode = errorCodeEnum.getErrorCode();
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }


    public String getErrCode() {
        return errCode;
    }
}
