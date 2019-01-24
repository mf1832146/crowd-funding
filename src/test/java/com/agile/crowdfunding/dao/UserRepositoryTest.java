package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.util.Page;
import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Create by tang ze on 2019/1/20 15:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    //@Test
    public void insertData(){
        //插入数据
        User user = new User();
        user.setUserName("test");
        user.setPhone("1283");
        user.setPassword("123");

        userRepository.save(user);

        List<User> userList = userRepository.findAll();
        System.out.println(JSONObject.toJSONString(userList));
    }

    @Test
    public void queryByPage(){
        Page page = new Page();
        page.setStart(0);
        page.setCount(10);

        List<User> list  = userRepository.listUsers(page.getStart(),page.getCount());

        System.out.println(list.size());
    }
}
