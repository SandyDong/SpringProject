package com.dong.service;

import com.dong.exception.PriceException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 *Spring框架和junit单元测试框架混合使用
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/SpringApplication.xml"})
public class PriceServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(PriceServiceTest.class);

    @Autowired
    private PriceService priceService;
    @Test
    public void priceHandleV2() throws Exception{
      logger.info("开始进行单元测试!");
      try {
          priceService.priceHandleV2(new Integer(180));
      }catch (Exception e){
          //对抛出的异常信息断言
          Assert.assertEquals(PriceException.class,e.getClass());
          if (e instanceof PriceException){
           logger.error(((PriceException) e).getErrCode()+e.getMessage());
          }else{
           logger.error(e.getMessage());
          }

      }
    }
}