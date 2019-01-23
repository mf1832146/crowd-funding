package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Create by tang ze on 2019/1/23 20:44
 */
public interface OrderRepository extends JpaRepository<Order,String> {

    public Order getFirstByOrderId(String orderId);

    @Query(value="UPDATE Order order SET order.state= 1 WHERE order.orderId= ?1")
    public void updateState(String orderId);
}
