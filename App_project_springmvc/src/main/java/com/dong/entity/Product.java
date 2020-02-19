package com.dong.entity;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 商品实体
 */
public class Product implements Serializable {

    @NotEmpty(message="产品姓名不能为空")
    private String name;
    @Min(value = 100,message = "此标价小于最低定价标准!")
    @NotEmpty(message = "输入的价格不能为空!")
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
