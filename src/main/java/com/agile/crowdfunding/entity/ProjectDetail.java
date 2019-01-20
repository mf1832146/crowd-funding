package com.agile.crowdfunding.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Create by tang ze on 2019/1/20 16:21
 * 项目详情
 */
@Entity
@Table(name = "CROWD_FUNDING_PROJECT_DETAIL")
public class ProjectDetail {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String projectDetailId;

    //项目标题
    @Column
    private String title;

    //项目目的
    @Column
    private String purpose;

    //项目标签
    @Column
    private String labels;

    //....待完善
}
