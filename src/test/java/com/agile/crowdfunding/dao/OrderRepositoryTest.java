package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.entity.Project;
import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.service.OrderService;
import com.agile.crowdfunding.service.ProjectService;
import com.agile.crowdfunding.service.UserService;
import com.agile.crowdfunding.vo.UserAndSups;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;

    @Test
    public void testAInsertOrder(){
        Order o1 = new Order();
        o1.setState(0);
        o1.setMoney(1000.0);
        o1.setOrderName("第一笔");
        o1.setProject(projectService.getProject("1"));
        o1.setUser(userService.getById("40289f23687aa67501687aa6d4f90000"));
        orderService.saveOrder(o1);
        Order o2 = new Order();
        o2.setState(1);
        o2.setMoney(1000.0);
        o2.setOrderName("第二笔");
        o2.setProject(projectService.getProject("1"));
        o2.setUser(userService.getById("40289f23687aa67501687aa6d4f90000"));
        orderService.saveOrder(o2);
    }


    // 通过id获取订单
    @Test
    public void testBGetFirstByOrderId(){
        System.out.println(orderRepository.getFirstByOrderId("402881eb68889bde0168889bfe2b0003").getOrderId());
    }

    //通过id获取用户和支持者信息
    @Test
    public void testCGetOrdersByUserId(){
        List<UserAndSups> userAndSupsList = orderRepository.getOrdersByUserId("40289f23687aa67501687aa6d4f90000");
        for (int i = 0; i < userAndSupsList.size(); i++){
            System.out.println(userAndSupsList.get(i).getOrderId());
        }
    }

    //获取已经支付完成的订单
    @Test
    public void testDGetOrdersPayed(){
        List<Order> payedList = orderRepository.getOrdersPayed("40289f23687aa67501687aa6d4f90000");
        for (int i =0; i < payedList.size();i++){
            System.out.println(payedList.get(i).getOrderId());
        }
    }

    //获取未支付完成的信息
    @Test
    public void testEGetOrdersNoPayed(){
        List<Order> noPayedList = orderRepository.getOrdersNoPayed("40289f23687aa67501687aa6d4f90000");
        for (int i =0; i < noPayedList.size();i++){
            System.out.println(noPayedList.get(i).getOrderId());
        }
    }

    //通过项目id获取订单
    @Test
    public void testFGetOrdersByProId(){
        List<Order> orderList = orderRepository.getOrdersByProId("1");
        for (int i =0; i < orderList.size();i++){
            System.out.println(orderList.get(i).getOrderId());
        }
    }

    // 分页列出所有的订单
    @Test
    public void testGListOrders(){
        System.out.println(orderRepository.listOrders(0,15).size());
    }

    // 更新状态
    @Transactional
    @Test
    public void testHUpdateStateByOrderId(){
        List<Order> orderList = orderRepository.getOrdersByProId("1");
        //orderRepository.updateStateByOrderId(orderList.get(0).getOrderId());
    }

    // 删除订单
    @Transactional
    @Test
    public void testIDeleteOrderByOrderId(){
        List<Order> orderList = orderRepository.getOrdersByProId("1");
        for (int i = 0; i < orderList.size(); i++){
            orderRepository.deleteOrderByOrderId(orderList.get(i).getOrderId());
        }
    }

    @Test
    public void testSave(){
        Order order = new Order();
        order.setOrderName("test");
        Project project = projectService.getProject("1");
        User user = userService.getById("2c93f9fd686a4bd001686a4be4240000");

        order.setProject(project);
        order.setUser(user);
        orderRepository.save(order);
        System.out.println(order.getOrderId());
    }
}

