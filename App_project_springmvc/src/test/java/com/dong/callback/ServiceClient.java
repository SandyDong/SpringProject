package com.dong.callback;

/**
 * 服务调用端
 */
public class ServiceClient implements ICallBack{

    public void  invokeServerMethod(){
        ServiceServer  serviceServer = new ServiceServer();
        serviceServer.getUserInfo(this);
    }

    public void doSendMessage() {
        System.out.println("回调函数所执行的逻辑");
    }

    /**
     * 测试函数
     * @param args
     */
    public static void main(String[] args) {
            ServiceClient   serviceClient  = new ServiceClient();
            serviceClient.invokeServerMethod();
    }
}
