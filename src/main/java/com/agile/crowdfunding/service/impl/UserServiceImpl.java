package com.agile.crowdfunding.service.impl;

import com.agile.crowdfunding.dao.UserRepository;
import com.agile.crowdfunding.entity.User;
import com.agile.crowdfunding.exception.GlobalException;
import com.agile.crowdfunding.result.CodeMsg;
import com.agile.crowdfunding.service.MessageService;
import com.agile.crowdfunding.service.UserService;
import com.agile.crowdfunding.util.Page;
import com.agile.crowdfunding.vo.ChangePWDVO;
import com.agile.crowdfunding.vo.LoginVo;
import com.agile.crowdfunding.vo.RegisterVo;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Create by tang ze on 2019/1/21 18:20
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageService messageService;

    public void insertUser(User user) {
        userRepository.save(user);
    }

    public User getById(String userId) {
        return userRepository.findFirstByUserId(userId);
    }

    public User getByName(String username) {
        return userRepository.findFirstByUserName(username);
    }

    public User getByPhone(String phone) {
        return userRepository.findFirstByPhone(phone);

    }

    public User getByMail(String mail) {
        return userRepository.findFirstByMail(mail);
    }

    public List<User> listUsers(Page page) {
        if(page.getCount() <= 0 || page.getStart() < 0){
            return userRepository.findAll();
        }
        return userRepository.listUsers(page.getStart(),page.getCount());
    }


    public void updateUserPassword(String userId, String password) {
        User user = userRepository.findFirstByUserId(userId);
        user.setPassword(password);
        userRepository.save(user);
    }

    public void deleteById(String userId) {
        userRepository.deleteByUserId(userId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }


    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String username = loginVo.getUsername();
        User user = getByName(username);
        if (user == null) {
            user = getByPhone(username);
            if (user == null) {
                user = getByMail(username);
                if (user == null) {
                    throw new GlobalException(CodeMsg.PHONE_NOT_EXIST);
                }
            }
        }
        String password = loginVo.getPassword();
        if (!password.equals(user.getPassword())) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return user.getUserId();
    }

    public String register(HttpServletResponse response, RegisterVo registerVo) {
        if (registerVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String username = registerVo.getUsername();
        User user = getByName(username);
        if (user != null) {
            // TODO:
            throw new GlobalException(CodeMsg.USERNAME_EXIST);
        }
        if (registerVo.getType() == 1) {
            user = getByPhone(registerVo.getUsermailOrPhone());
            if (user != null)
                throw new GlobalException(CodeMsg.USERPHONE_EXIST);
        }
        if (registerVo.getType() == 2) {
            user = getByMail(registerVo.getUsermailOrPhone());
            if (user != null)
                throw new GlobalException(CodeMsg.USERMAIL_EXIST);
        }
        User record = new User();
        record.setUserName(username);
        record.setPassword(registerVo.getPassword());
        if (registerVo.getType() == 1)
            record.setPhone(registerVo.getUsermailOrPhone());
        if (registerVo.getType() == 2)
            record.setMail(registerVo.getUsermailOrPhone());
        insertUser(record);

        messageService.sendMessageToSponstor(userRepository.findFirstByUserName(username).getUserId(), "【欢迎注册】 恭喜您注册成功！！");
        return "success";
    }

    public String changePWD(HttpServletResponse response, ChangePWDVO changePWDVo) {
        if (changePWDVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String phone = null;
        String mail = null;
        if (changePWDVo.getType() == 1)
            phone = changePWDVo.getPhoneOmail();
        else
            mail = changePWDVo.getPhoneOmail();
        String newPWD = changePWDVo.getNewPWD();

        String IDcode = changePWDVo.getIDcode();
        // *******
        // 在这里对验证码进行验证
        // *******

        User user = getByPhone(phone);
        if (user == null) {
            user = getByMail(mail);
            if (user == null) {
                throw new GlobalException(CodeMsg.PHONE_NOT_EXIST);
            }
        }

        updateUserPassword(user.getUserId(), newPWD);
        return "success";
    }

    public Integer total() {
        return userRepository.countByUserIdIsNot("-1").intValue();
    }


}
