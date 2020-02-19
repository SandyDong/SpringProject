package com.dong.callback;

/**
 * 计算程序运行时间
 */
public class SumRuntime {
    
    public  static  void testMethod(){
        for (int i = 0; i <100000 ; i++) {
            System.out.println("aaaaa"+i);
        }
    }

    public static void main(String[] args) {
        long  beginTime = System.currentTimeMillis();
        testMethod();
        System.out.println(System.currentTimeMillis() - beginTime);
    }
}

 