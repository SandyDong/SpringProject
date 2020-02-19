package com.dong.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 订单信息查询服务
 */
@Service
public class OrderInfoQueryService {

    public String getOrderInfo(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("苹果","10");
        jsonObject.put("西瓜","5");
        jsonObject.put("香蕉","3");
        return jsonObject.toJSONString();
    }
}
