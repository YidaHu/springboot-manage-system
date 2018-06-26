package com.huyd.service;

import com.huyd.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: huyida
 * Date: 2018/6/25
 * Time: 16:43
 * Description:
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 添加用户
     *
     * @param user
     */
    int saveUser(User user);
}
