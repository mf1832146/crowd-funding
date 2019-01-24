package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.util.Page;
import com.agile.crowdfunding.vo.UserAndSups;

import java.util.List;

/**
 * Create by tang ze on 2019/1/23 20:33
 */
public interface OrderService {

    void insertOrder(Order order);

    Order getById(String orderId);

    List<Order> listOrders(Page page);

    List<UserAndSups> getOrdersByUserId(String userId);

    List<Order> getOrdersPayed(String id);

    List<Order> getOrdersNoPayed(String id);

    List<Order> getOrdersByProId(String id);

    void updateState(String orderId);

    void deleteOrder(String id) ;

    Order getOrderByOrderId(String orderId);

    void saveOrder(Order order);

}
