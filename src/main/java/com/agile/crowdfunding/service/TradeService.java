package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.Trade;

import java.util.List;

/**
 * Create by tang ze on 2019/1/23 20:30
 * 交易
 */
public interface TradeService {
    void saveTrade(Trade trade);
    List<Trade> getTrades(String id);
}
