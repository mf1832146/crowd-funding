package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.ProAndUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProAndUsersRepository extends JpaRepository<ProAndUsers, String> {

    List<ProAndUsers> findByProjectId(String projectId);
}
