package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create by tang ze on 2019/1/20 15:34
 */
public interface TradeRepository extends JpaRepository<Trade,String> {
    List<Trade> getAllByUserUserId(String userId);
}
