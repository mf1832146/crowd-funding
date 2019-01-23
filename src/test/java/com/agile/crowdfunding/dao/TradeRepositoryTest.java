package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Trade;
import com.agile.crowdfunding.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Create by tang ze on 2019/1/20 16:00
 */

public class TradeRepositoryTest {

    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void insert(){
        User user = userRepository.findAll().get(0);
        Trade trade = new Trade();
        trade.setMoney(11.1);
        trade.setUser(user);

        tradeRepository.save(trade);

       Trade trade1 = tradeRepository.findAll().get(0);
       System.out.println(trade1.toString());
    }

}
