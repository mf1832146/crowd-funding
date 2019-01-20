package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Create by tang ze on 2019/1/20 15:34
 */
public interface TradeRepository extends JpaRepository<Trade,String> {
}
