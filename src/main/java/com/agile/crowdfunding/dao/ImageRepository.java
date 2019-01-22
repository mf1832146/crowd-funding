package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {

    List<Image> findByProjectId(String projID);

}
