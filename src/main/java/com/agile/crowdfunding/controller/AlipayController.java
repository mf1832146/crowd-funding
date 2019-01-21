package com.agile.crowdfunding.controller;

import com.agile.crowdfunding.service.AlipayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by tang ze on 2019/1/21 16:00
 * alipay
 */
@Controller
@RequestMapping("/pay")
public class AlipayController {

    private static Logger logger = LoggerFactory.getLogger(AlipayController.class);

    @Autowired
    AlipayService alipayService;

}
