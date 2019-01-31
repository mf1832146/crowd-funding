package com.agile.crowdfunding.controller;

import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.entity.Trade;
import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.service.*;
import com.agile.crowdfunding.util.AuthorizationUtils;
import com.agile.crowdfunding.vo.OrderDetailsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Create by tang ze on 2019/1/23 20:27
 * 支付
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    private static Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private AuthorizationUtils auth;
    @Autowired
    private MessageService messageService;
    @Autowired
    private TradeService tradeService;

    //去支付页面
    @RequestMapping("/toPay")
    public String toPay() {
        return "/fore/pay/pay";
    }

    //支付成功返回处理
    @RequestMapping(value = "/return", method = RequestMethod.GET)
    public String return_get(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {

        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //更新订单状态 进行资金交易
        orderService.updateState(out_trade_no);



        Trade trade = new Trade();
        trade.setMoney(orderService.getOrderByOrderId(out_trade_no).getMoney());
        trade.setInfo("支付宝支付");

        String userId =(String) session.getAttribute("myId");
        User user = userService.getById(userId);
        trade.setUser(user);
        tradeService.saveTrade(trade);

        Order order = orderService.getOrderByOrderId(out_trade_no);
        Double money = order.getMoney();
        String projectId = orderService.getOrderByOrderId(out_trade_no).getProject().getProjectId();
        String info = "【支付宝付】关于项目【" + projectService.getProject(projectId).getName() + "】，" + money + "元";

        messageService.sendMessageToSponstor(userId, info );

        projectService.saveProAndUser(order);

        if(projectService.getProject(projectId).getCurrentMoney() > projectService.getProject(projectId).getTargetMoney()) {
            projectService.updateState(projectId, 31);
        }

        //new 2018 7 11
        return "redirect:/detail/showDetail";

    }


    //支付请求处理
    @RequestMapping(value = "/alipay", method = RequestMethod.POST)
    public String aliPay(Model model, OrderDetailsVO orderDetailsVO, String uc_payChoice, String orderId){
        if(orderId == null) {
            System.out.println("idInteger null");
            return "redirect:/fore/home";
        }
        if(uc_payChoice.equals("1")) {
            String result = alipayService.orderRequest(orderId);
            model.addAttribute("result", result);
            return "/fore/pay/alipay";
        }
        if(uc_payChoice.equals("2")) {

            return "redirect:/fore/home";
        }
        if(uc_payChoice.equals("3")) {
            if(alipayService.moneyPay(orderDetailsVO, orderId)) {
                return "redirect:/fore/home";
            }else {
                return "redirect:/fore/home";
            }

        }
        return null;
    }


    //新订单
    @RequestMapping("/newAalipay")
    public String newAalipay(HttpSession session,Model model,Double money, Integer orderType,String projectId){

        if (!auth.check(session))
            return "redirect:/login/toLogin";
        String userId =(String) session.getAttribute("myId");

        if(orderType == 1) {
            String result = alipayService.newOrderByAlipay(userId, money, projectId);
            model.addAttribute("result", result);
            return "/fore/pay/alipay";
        }
        if(orderType == 2) {
            return "redirect:/detail/showDetail?"+ projectId;
        }
        if(orderType == 3) {
            if(alipayService.newMoneyPay(userId, money, projectId)) {
                return "redirect:/detail/showDetail?projID="+ projectId;
            }
            else {
                return "forward:/pay/poor";
            }

        }
        return null;
    }

    @RequestMapping("/poor")
    @ResponseBody
    public String poor() {


        return "余额不足";
    }

}
