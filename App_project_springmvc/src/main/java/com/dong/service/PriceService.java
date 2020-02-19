package com.dong.service;

import com.dong.enumclass.ErrorCodeEnum;
import com.dong.exception.PriceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("priceService")
public class PriceService {

    private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

    /**
     * 直接使用exception不能设置错误码，因此也不利于排查错误信息
     * 因此需要自定义一个异常信息类
     * @param price
     * @throws Exception
     */
    public void priceHandleV1(Integer price) throws Exception{
        if (price < 120 ){
          throw new Exception("当前价格不符合成本要求");
        } else if (price > 180) {
          throw new Exception("当前价格不符合监管要求");
        }
    }

    public void priceHandleV2(Integer price) throws Exception{
        if (price < 0){
            throw new Exception("定价数字不规范！");
        }
        if (price >= 0 && price < 120 ){
            throw new PriceException(ErrorCodeEnum.PRICE_IS_LESS);
        }
        if (price >=120 && price <=180){
            logger.info("当前产品定价属于正常范围!");
        }
        if (price > 180) {
            throw new PriceException(ErrorCodeEnum.PRICE_IS_HIGH);
        }
    }
}
