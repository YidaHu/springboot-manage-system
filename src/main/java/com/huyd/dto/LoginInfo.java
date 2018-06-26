package com.huyd.dto;

import com.huyd.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: huyida
 * Date: 2018/6/26
 * Time: 14:15
 * Description:
 */
public class LoginInfo {
    User user;
    String token;
    String message;

    public LoginInfo(User user, String token, String message) {
        this.user = user;
        this.token = token;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
