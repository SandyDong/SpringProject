package com.dong.controller.Request;

import com.dong.common.Result;
import com.dong.entity.Product;
import com.dong.enumclass.ErrorCodeEnum;
import com.dong.exception.PriceException;
import com.dong.quartz.GetDataEveryMinuteJob;
import com.dong.service.PriceService;
import com.dong.utils.CommonResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * 1 测试json数据的接收,返回处理
 * 2 使用AOP技术处理请求
 * 3 支持参数验证hibernate -final去验证
 * 4 由于不同的处理后的情况返回不同的状态信息,需要统一下结果格式返回给客户端。
 * 5 可以使用工具类优化处理4中出现的重复代码。
 * 6 统一异常信息处理(可以继承runtimeexception自定义一个异常类)
 * 7 单元测试（注意controller层和service,dao层的测试不同点）spring + junit进行测试
 * 8 foreach的时候一定要对要遍历的元素进行null判定。
 * 9 foreach中不要对遍历的元素进行增删改操作（问题严重）
 *10 使用了的流一定要关闭。一定要关闭。
 *
 *
 */
@RestController
public class RestControllerJson  {

    @Resource(name = "priceService")
    private PriceService priceService;

    private static final Logger logger = LoggerFactory.getLogger(RestControllerJson.class);

    @RequestMapping(value = "/getProductMessage",
            method = RequestMethod.POST)
    public Result<Product> getProductInfo(@Valid Product product, BindingResult bindingResult){
        Result<Product>  result = new Result<Product>();

         //如果有返回错误信息，可以打印输出
        if (bindingResult.hasErrors()){
            result.setCode("600");
            //对校验出现多个字段异常信息的情况
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            StringBuffer stringBuffer = new StringBuffer();
            if(!fieldErrorList.isEmpty()&&null!=fieldErrorList){
                //foreach前进行非空判断
                for (FieldError fieldError:fieldErrorList){
                    logger.error(bindingResult.getFieldError().getDefaultMessage());
                    stringBuffer.append(" ["+fieldError.getDefaultMessage()+"] ");
                }
            }
            result.setMessage(stringBuffer.toString());
            result.setData(null);
            return result;
        }
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        result.setCode("200");
        result.setMessage("调用接口成功!");
        result.setData(product);
        return result;
    }

    /**
     * 如果按照上述写法还是出现同样的代码重复出现。因此可以再次去优化,编写一个工具类处理成功或者失败的场景
     * @param product
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/getProductMessageV1",
            method = RequestMethod.POST)
    public Result<Product> getProductInfoV1(@Valid Product product, BindingResult bindingResult){
        //如果有返回错误信息，可以打印输出
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            StringBuffer stringBuffer = new StringBuffer();
            for (FieldError fieldError:fieldErrorList){
                logger.error(bindingResult.getFieldError().getDefaultMessage());
                stringBuffer.append(" ["+fieldError.getDefaultMessage()+"] ");
            }
            return CommonResultUtils.error("508",stringBuffer.toString());
        }
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        return CommonResultUtils.success(product);
    }

    /**
     * 由于该方法是直接将异常信息返给客户端，因此错误信息展示也不友好。也不便于排查错误信息
     * 所以需要进行异常信息捕获处理。
     * @param id
     * @param product
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getProductInfoV1/{id}",
            method = RequestMethod.POST)
    public  Product getProductInfoV1(@PathVariable("id")Integer id, Product product) throws Exception{
        logger.info("id==={}",id);
        logger.info("name==={}",product.getName());
        logger.info("price==={}",product.getPrice());
        String  price = product.getPrice();
        if(StringUtils.hasText(price)){
            priceService.priceHandleV1(Integer.valueOf(price));
        }else{
            throw new Exception("当前price价格为空!");
        }
        return product;
    }

    @RequestMapping(value = "/getProductInfoV2/{id}",
            method = RequestMethod.POST)
    public  Result getProductInfoV2(@PathVariable("id")Integer id, Product product) throws Exception{
        logger.info("id==={}",id);
        logger.info("name==={}",product.getName());
        logger.info("price==={}",product.getPrice());
        String  price = product.getPrice();
        if(StringUtils.hasText(price)){
            priceService.priceHandleV2(Integer.valueOf(price));
        }else{
            throw new PriceException(ErrorCodeEnum.UNKNOWN_ERROR);
        }
        return CommonResultUtils.success(product);
    }

    @Resource(name = "dayQuartz")
    private GetDataEveryMinuteJob  getDataEveryMinuteJob;
    /**
     *
     * 开一个rest接口动态设置定时器的间隔刷新时间
     */
    @RequestMapping(value = "/restTime",method = RequestMethod.POST)
    public void setIntervalTime(long time)throws Exception{
        getDataEveryMinuteJob.restJob(time);
    }
}


