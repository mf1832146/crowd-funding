package com.agile.crowdfunding.service.impl;

import com.agile.crowdfunding.dao.TradeRepository;
import com.agile.crowdfunding.entity.Trade;
import com.agile.crowdfunding.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Create by tang ze on 2019/1/23 20:30
 */
@Service
public class TradeServiceImpl implements TradeService {
    private static Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public void saveTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    @Override
    public List<Trade> getTrades(String id) {
        return tradeRepository.getAllByUserUserId(id);
    }
}
