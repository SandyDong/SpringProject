package com.dong.test;

import org.junit.Test;

import java.util.*;

/**
 * 测试Map
 */
public class MapTest {

    public static void main(String[] args) {
        System.out.println("打印日志输出");
        Map<String,String>  map = new HashMap<String, String>();
        map.put("aa","11");
        map.put("bb","22");
        map.put("cc","33");
        //通过 keySet 方法遍历hashmap 通过两次取值的方式
        for (String m:map.keySet()){
            System.out.println(map.get(m));
        }
        //通过迭代器输出 entrySet (这种需要强转类型，不推荐使用)
        Iterator  iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
             Map.Entry<String,String>  mm = (Map.Entry<String, String>) iterator.next();
             System.out.println(mm.getKey()+"---->"+mm.getValue());
        }
        //直接通过遍历entrySet输出,容量大，推荐使用.
        for (Map.Entry<String,String> mq:map.entrySet()){
             System.out.println(mq.getKey()+"----->"+mq.getValue());
        }
        //直接遍历Map中的value值，不涉及到key
             int i=1;
        for (String str:map.values()){
             System.out.println("the "+(i++)+" value is  "+str);
        }
    }
//    @Test
    public void  printMessage()throws Exception{
        Thread.sleep(10000);
        System.out.println("打印出信息");
    }
}
