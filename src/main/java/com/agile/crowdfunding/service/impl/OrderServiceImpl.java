package com.agile.crowdfunding.service.impl;

import com.agile.crowdfunding.dao.OrderRepository;
import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.service.OrderService;
import com.alipay.api.domain.OrderRefundInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by tang ze on 2019/1/23 20:43
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void updateState(String orderId) {
        orderRepository.updateStateByOrderId(orderId);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        return orderRepository.getFirstByOrderId(orderId);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }


}
