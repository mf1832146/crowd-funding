package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.vo.UserAndSups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Create by tang ze on 2019/1/23 20:44
 */
public interface OrderRepository extends JpaRepository<Order,String> {

    // 通过id获取订单
    Order getFirstByOrderId(String orderId);

    //通过id获取用户和支持者信息
    @Query(value = "select * from user_and_sups where user_id = ?1", nativeQuery = true)
    List<UserAndSups> getOrdersByUserId(String userId);

    //获取已经支付完成的订单
    @Query(value = "select * from crowd_funding_order where user_id = ?1 and state = 1", nativeQuery = true)
    List<Order> getOrdersPayed(String userId);

    //获取未支付完成的信息
    @Query(value = "select * from crowd_funding_order where user_id = ?1 and state = 0", nativeQuery = true)
    List<Order> getOrdersNoPayed(String userId);

    //通过项目id获取订单
    @Query(value = "select * from crowd_funding_order where project_id = ?", nativeQuery = true)
    List<Order> getOrdersByProId(String proId);

    // 分页列出所有的订单
    @Query(value ="select * from crowd_funding_order c "
            + "limit ?1,?2 ",nativeQuery = true)
    List<Order> listOrders(int start, int num);

    // 更新状态
    @Query(value="UPDATE crowd_funding_order o SET o.state= 1 WHERE o.orderId= ?1")
    void updateStateByOrderId(String orderId);

    // 删除订单
    void deleteOrderByOrderId(String orderId);
}
