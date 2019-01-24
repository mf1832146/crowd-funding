package com.agile.crowdfunding.service;

import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.result.Result;
import com.agile.crowdfunding.util.Page;
import com.agile.crowdfunding.vo.ChangePWDVO;
import com.agile.crowdfunding.vo.LoginVo;
import com.agile.crowdfunding.vo.RegisterVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Create by tang ze on 2019/1/21 18:05
 */
public interface UserService {

    void insertUser(User user);
    User getById(String userId);
    User getByName(String username);
    User getByPhone(String phone);
    User getByMail(String mail);
    List<User> listUsers(Page page);
    void updateUserPassword(String userId, String password);
    void deleteById(String userId);
    void updateUser(User user);
    String login(HttpServletResponse response, LoginVo loginVo);
    String register(HttpServletResponse response, RegisterVo registerVo);
    String changePWD(HttpServletResponse response, ChangePWDVO changePWDVo);
    Integer total();

}
