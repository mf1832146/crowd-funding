package com.agile.crowdfunding.dao;

import com.agile.crowdfunding.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Create by tang ze on 2019/1/20 15:35
 */
public interface UserRepository extends JpaRepository<User,String> {

    public User findFirstByUserNameAndPassword(String userName,String password);

    public User findFirstByUserId(String userId);

    public User findFirstByUserName(String userName);

    public User findFirstByPhone(String phone);

    public User findFirstByMail(String mail);

    public void deleteByUserId(String userId);

    public Long countByUserIdIsNot(String userId);

    @Query(value ="select * from crowd_funding_user c "
            + "limit ?1,?2 ",nativeQuery = true)
    public List<User> listUsers(int start,int num);

}
