package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.util.Page;
import com.agile.crowdfunding.vo.UserAndSups;

import java.util.List;

/**
 * Create by tang ze on 2019/1/23 20:33
 */
public interface OrderService {

//    public void insertOrder(Order order);
//
//    public Order getById(String orderId);
//
//    public List<Order> listOrders(Page page);
//
//    public List<UserAndSups> getOrdersByUserId(String userId);
//
//    public List<Order> getOrdersPayed(String id);
//
//    public List<Order> getOrdersNoPayed(String id);
//
//    public List<Order> getOrdersByProId(int id);
//
    public void updateState(String orderId);
//
//    public void deleteOrder(Integer id) ;

    public Order getOrderByOrderId(String orderId);

    public void saveOrder(Order order);

}
