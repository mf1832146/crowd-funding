package com.agile.crowdfunding.util;

import com.agile.crowdfunding.entity.Order;
import com.agile.crowdfunding.service.ProjectService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyAlipayUtils {
	
	@Autowired
	static ProjectService projectService;
	
	public static String pay(Order order) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = order.getOrderId();
		// 付款金额，必填
		String total_amount = order.getMoney().toString();
		// 订单名称，必填
		String subject =  order.getOrderName();
		// 商品描述，可空
		String body = order.getUser().getUserId() + "has supported project" + order.getProject().getProjectId();

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//返回结果是 html代码  
 		return alipayClient.pageExecute(alipayRequest).getBody();
	}
}
