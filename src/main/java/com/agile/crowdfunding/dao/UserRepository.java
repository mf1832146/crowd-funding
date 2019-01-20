package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by tang ze on 2019/1/20 15:35
 */
public interface UserRepository extends JpaRepository<User,String> {
}
