package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.Order;

/**
 * Create by tang ze on 2019/1/21 15:05
 */
public interface AlipayService {

    //支付
    public String pay(Order order);

}
