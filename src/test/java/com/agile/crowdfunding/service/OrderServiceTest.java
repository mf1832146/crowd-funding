package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;

    @Test
    public void testInsertOrder(){
        Order o1 = new Order();
        o1.setOrderId("001");
        o1.setState(0);
        o1.setMoney(1000.0);
        o1.setOrderName("第一笔");
        o1.setProject(projectService.getProject("1"));
        o1.setUser(userService.getById("40289f23687aa67501687aa6d4f90000"));
        orderService.saveOrder(o1);
        Order o2 = new Order();
        o2.setOrderId("002");
        o2.setState(1);
        o2.setMoney(1000.0);
        o2.setOrderName("第二笔");
        o2.setProject(projectService.getProject("1"));
        o2.setUser(userService.getById("40289f23687aa67501687aa6d4f90000"));
        orderService.saveOrder(o2);
    }

    @Test
    public void testDeleteOrder(){
        List<Order> list = orderService.getOrdersByProId("1");
        for (int i = 0; i < list.size(); i++) {
            orderService.deleteOrder(list.get(i).getOrderId());
        }
    }
}
