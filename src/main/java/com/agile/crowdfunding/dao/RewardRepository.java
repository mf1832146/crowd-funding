package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, String> {

    public Reward findByProjectId(String projectId);
}
