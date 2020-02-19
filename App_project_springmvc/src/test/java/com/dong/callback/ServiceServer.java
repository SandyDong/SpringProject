package com.dong.callback;

/**
 * 调用的服务端
 */
public class ServiceServer {
    /**
     * 获取用户信息
     */
    public  void  getUserInfo(ServiceClient serviceClient){
        System.out.println("输出用户信息");
        serviceClient.doSendMessage();
    }

}
