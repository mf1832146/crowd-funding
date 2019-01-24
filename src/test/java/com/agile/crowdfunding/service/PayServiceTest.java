package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.entity.Project;
import com.agile.crowdfunding.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by tang ze on 2019/1/21 15:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceTest {
    @Autowired
    private AlipayService alipayService;

    //支付测试
//    @Test
//    public void payTest(){
//        Order order = new Order();
//
//        order.setOrderId("1");
//        order.setMoney(0.01);
//        order.setOrderName("test");
//
//        User user = new User();
//        user.setUserName("111");
//        order.setUser(user);
//
//        Project project = new Project();
//        project.setProjectId("1");
//        order.setProject(project);
//
//
//
//        System.out.println(alipayService.pay(order));
//    }

}
