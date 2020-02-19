package com.dong.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义一个切面处理http请求类
 */
@Aspect
@Component
public class HttpRequestAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpRequestAspect.class);

    /**
     * 添加日志访问的切面处理
     */
    /*@Before("execution(public * com.dong.controller.Request.RestControllerJson.getProductInfo(..))")
    public void printLog(){
       System.out.println("控制台输出请求前访问的切面日志");
    }
*/
   /* @After("execution(public * com.dong.controller.Request.RestControllerJson.getProductInfo(..))")
    public void doAfterLog(){
        System.out.println("控制台输出请求后访问的切面日志");
    }*/


    //如果按照上述情况编写，也会造成冗余的代码出现,可以设置一个切割点pointcut
    @Pointcut("execution(public * com.dong.controller.Request.RestControllerJson.getProductInfo(..))")
    public void commonLog(){
    }

    /**
     * 可以通过joint获取所请求的类，方法名，参数，连接点
     * 可以通过RequestContextHolder获取http请求对象，进而获取请求的一些参数
     * @param joinPoint
     */
    @Before("commonLog()")
    public void printLog(JoinPoint joinPoint){
        ServletRequestAttributes  servletRequestAttributes  = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = servletRequestAttributes.getRequest();
        logger.info("auth_type={}",servletRequest.getAuthType());
        logger.info("request_method={}",servletRequest.getMethod());
        logger.info("request_url={}",servletRequest.getRequestURI());
        logger.info("client_ip={}",servletRequest.getRemoteAddr());
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        Object[] object = joinPoint.getArgs();
        for(Object ob:object){
            logger.info("method_argus={}",ob.toString() );
        }
        System.out.println("控制台输出请求前访问的切面日志");
    }

    @After("commonLog()")
    public void doAfterLog(){
        System.out.println("控制台输出请求后访问的切面日志");
    }

    /**
     * 需要对返回的结果进行处理，所以需要能获取到返回结果值,也就是在返回之前进行处理
     */
    @AfterReturning(returning = "object",pointcut = "commonLog()")
    public  void doAfterReturnResult(Object object){
        logger.info("response-body={}",object.toString());
    }
}
