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

    public void insertUser(User user);
    public User getById(String userId);
    public User getByName(String username);
    public User getByPhone(String phone);
    public User getByMail(String mail);
    public List<User> listUsers(Page page);
    public void updateUserPassword(String userId, String password);
    public void deleteById(String userId);
    public void updateUser(User user);
    public String login(HttpServletResponse response, LoginVo loginVo);
    public String register(HttpServletResponse response, RegisterVo registerVo);
    public String changePWD(HttpServletResponse response, ChangePWDVO changePWDVo);
    public Long total();

}
