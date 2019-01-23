package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.vo.OrderDetailsVO;

/**
 * Create by tang ze on 2019/1/21 15:05
 */
public interface AlipayService {

    public String request(OrderDetailsVO orderDetailsVO);
    public String orderRequest(String orderId);
    public boolean moneyPay(OrderDetailsVO orderDetailsVO, String orderId);
    public String newOrderByAlipay(String userId,Double money, String projectId);
    public boolean newMoneyPay(String userId,Double money, String projectId);


}
