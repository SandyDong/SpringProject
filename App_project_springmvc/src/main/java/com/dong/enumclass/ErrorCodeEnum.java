package com.dong.enumclass;

/**
 * 当包含多种异常信息处理
 * 由于异常枚举类在构造方法设置参数,不需要set方法
 *
 */
public enum ErrorCodeEnum {

    UNKNOWN_ERROR("-1","未知异常信息"),
    PRICE_IS_LESS("109","定价低于行业标准"),
    PRICE_IS_HIGH("110","定价高于行业标准");

    private String errorCode;

    private String message;

     ErrorCodeEnum(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
