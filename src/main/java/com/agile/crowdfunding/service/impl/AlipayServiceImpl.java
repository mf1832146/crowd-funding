package com.agile.crowdfunding.service.impl;

import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.entity.Project;
import com.agile.crowdfunding.entity.Trade;
import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.exception.GlobalException;
import com.agile.crowdfunding.result.CodeMsg;
import com.agile.crowdfunding.service.*;
import com.agile.crowdfunding.util.AlipayConfig;
import com.agile.crowdfunding.util.MyAlipayUtils;
import com.agile.crowdfunding.util.RandomUtils;
import com.agile.crowdfunding.vo.OrderDetailsVO;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * Create by tang ze on 2019/1/21 15:06
 */
@Service
public class AlipayServiceImpl implements  AlipayService{
    private static Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TradeService tradeService;

    // 向支付宝平台发送请求
    public String request(OrderDetailsVO orderDetailsVO) {
        // 首先验证接收参数的合法性
        if (orderDetailsVO == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        System.out.println(orderDetailsVO.toString());
        User user = userService.getById(orderDetailsVO.getUserId());
        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
        Project project = projectService.getProject(orderDetailsVO.getProjectId());
        if (project == null) {
            throw new GlobalException(CodeMsg.PROJECT_NOT_EXIST);
        }

        // 生成订单 设置属性
        Order order = new Order();
        order.setOrderName("423众筹-" + project.getName());
        order.setUser(user);
        order.setProject(project);
        //order.setPhone("18000000000");
        order.setMoney(orderDetailsVO.getMoney());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
        String orderId = df.format(new java.util.Date());
        orderId += RandomUtils.getRandomLetterAndNum(7);
        order.setOrderId(orderId);

        // 时间默认当前
        order.setDate(new java.sql.Date(new java.util.Date().getTime()));

        // 插入订单
        //orderService.insertOrder(order);

        // 调用工具类发送请求 返回请求结果
        String result = null;
        try {
            result = MyAlipayUtils.pay(order);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (result == null) {
            throw new GlobalException(CodeMsg.ORDER_REQUEST_ERROR);
        }
        // 返回的result是 html代码
        return result;
    }


    //order pay
    public String orderRequest(String orderId) {
        // 首先验证接收参数的合法性
        if (orderId == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        Order order = orderService.getOrderByOrderId(orderId);

        // 调用工具类发送请求 返回请求结果
        String result = null;
        try {
            result = MyAlipayUtils.pay(order);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (result == null) {
            throw new GlobalException(CodeMsg.ORDER_REQUEST_ERROR);
        }
        // 返回的result是 html代码
        return result;
    }


    // 余额支付
    public boolean moneyPay(OrderDetailsVO orderDetailsVO, String orderId) {
        // 首先验证接收参数的合法性
        if (orderDetailsVO == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        User user = userService.getById(orderDetailsVO.getUserId());
        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
        Project project = projectService.getProject(orderDetailsVO.getProjectId());
        if (project == null) {
            throw new GlobalException(CodeMsg.PROJECT_NOT_EXIST);
        }
        if(user.getMoney() < orderDetailsVO.getMoney())
            return false;

        Trade trade = new Trade();
        trade.setMoney(orderDetailsVO.getMoney());
        trade.setInfo("余额支付");
        trade.setUser(user);
        tradeService.saveTrade(trade);

        if(project.getCurrentMoney() >project.getTargetMoney()) {
            projectService.updateState(orderDetailsVO.getProjectId(), 31);
        }

        orderService.updateState(orderId);
        return true;
    }

    //new pay  by alipay
    public String newOrderByAlipay(String userId,Double money, String projectId) {


        // 生成订单 设置属性
        Order order = new Order();
        order.setOrderName("423众筹-" + projectService.getProject(projectId).getName());
        User user = userService.getById(userId);
        order.setUser(user);
        Project project = projectService.getProject(projectId);
        order.setProject(project);
        order.setMoney(money);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
        String orderId = df.format(new java.util.Date());
        orderId += RandomUtils.getRandomLetterAndNum(7);
        order.setOrderId(orderId);

        // 时间默认当前
        order.setDate(new java.sql.Date(new java.util.Date().getTime()));

        // 插入订单
        orderService.saveOrder(order);

        // 调用工具类发送请求 返回请求结果
        String result = null;
        try {
            result = MyAlipayUtils.pay(order);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (result == null) {
            throw new GlobalException(CodeMsg.ORDER_REQUEST_ERROR);
        }
        // 返回的result是 html代码
        return result;


    }

    public boolean newMoneyPay(String userId,Double money, String projectId) {


        if(userService.getById(userId).getMoney() < money)
            return false;
        // 生成订单 设置属性
        Order order = new Order();
        order.setOrderName("423众筹-" + projectService.getProject(projectId).getName());
        User user = userService.getById(userId);
        Project project = projectService.getProject(projectId);
        order.setUser(user);
        order.setProject(project);
        order.setMoney(money);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
        String orderId = df.format(new java.util.Date());
        orderId += RandomUtils.getRandomLetterAndNum(7);
        order.setOrderId(orderId);

        // 时间默认当前
        order.setDate(new java.sql.Date(new java.util.Date().getTime()));

        // 插入订单
        orderService.saveOrder(order);
        orderService.updateState(orderId);

        Trade trade = new Trade();
        trade.setMoney(money);
        trade.setInfo("余额支付");
        trade.setUser(user);
        tradeService.saveTrade(trade);

        String info = "【余额支付】关于项目【" + projectService.getProject(projectId).getName() + "】，" + money + "元";
        messageService.sendMessageToSponstor(userId,info);


        if(projectService.getProject(projectId).getCurrentMoney() >projectService.getProject(projectId).getTargetMoney()) {
            projectService.updateState(projectId, 31);
        }


        return true;
    }

}
