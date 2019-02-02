package com.agile.crowdfunding.service.impl;

import com.agile.crowdfunding.dao.OrderRepository;
import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.entity.Project;
import com.agile.crowdfunding.service.OrderService;
import com.agile.crowdfunding.util.Page;
import com.agile.crowdfunding.util.StateUtils;
import com.agile.crowdfunding.vo.UserAndSups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Create by tang ze on 2019/1/23 20:43
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void insertOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getById(String orderId) {
        return orderRepository.getFirstByOrderId(orderId);
    }

    @Override
    public List<Order> listOrders(Page page) {
        if(page.getCount() <= 0 || page.getStart() < 0){
            return orderRepository.findAll();
        }
        return orderRepository.listOrders(page.getStart(),page.getCount());
    }

    @Override
    public List<UserAndSups> getOrdersByUserId(String userId) {
        List<Order> orderList = orderRepository.getOrdersByUserId(userId);
        List<UserAndSups> userAndSupsList = null;
        for (int i = 0; i < orderList.size(); i++) {
            UserAndSups userAndSups = new UserAndSups();
            Order order = orderList.get(i);
            Project project = orderList.get(i).getProject();

            userAndSups.setUserId(order.getUser().getUserId());
            userAndSups.setProjectId(project.getProjectId());
            userAndSups.setProjectName(project.getName());
            userAndSups.setTargetMoney(project.getTargetMoney());
            userAndSups.setCurrentMoney(project.getCurrentMoney());
            userAndSups.setMoney(order.getMoney());
            userAndSups.setState(order.getState());
            userAndSups.setDate(order.getDate());
            userAndSups.setProState(project.getState());
            userAndSups.setOrderId(order.getOrderId());
            userAndSupsList.add(userAndSups);
        }
        return userAndSupsList;
    }

    @Override
    public List<Order> getAllOrdersByUserId(String userId) {
        return orderRepository.getOrdersByUserId(userId);
    }

    @Override
    public List<Order> getOrdersPayed(String id) {
        return orderRepository.getOrdersPayed(id);
    }

    @Override
    public List<Order> getOrdersNoPayed(String id) {
        return orderRepository.getOrdersNoPayed(id);
    }

    @Override
    public List<Order> getOrdersByProId(String id) {
        return orderRepository.getOrdersByProId(id);
    }

    @Override
    public void updateState(String orderId) {
        orderRepository.updateStateByOrderId(orderId);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteOrderByOrderId(id);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        return orderRepository.getFirstByOrderId(orderId);
    }

    @Override
    public void saveOrder(Order order) {
        logger.info("保存订单信息");
        orderRepository.save(order);
    }


}
