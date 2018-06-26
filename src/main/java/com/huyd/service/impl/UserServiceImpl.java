package com.huyd.service.impl;

import com.huyd.domain.User;
import com.huyd.mapper.UserMapper;
import com.huyd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: huyida
 * Date: 2018/6/25
 * Time: 16:44
 * Description:
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(String username) {
        //没有此用户直接返回null
        User user = userMapper.findByName(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insertUser(user);
    }
}
