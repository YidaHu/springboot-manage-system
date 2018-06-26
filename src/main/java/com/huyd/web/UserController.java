package com.huyd.web;

import com.huyd.core.Result;
import com.huyd.core.ResultGenerator;
import com.huyd.domain.User;
import com.huyd.dto.LoginInfo;
import com.huyd.service.UserService;
import com.huyd.util.JWTUtil;
import com.huyd.util.SHA256Str;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: huyida
 * Date: 2018/6/25
 * Time: 16:49
 * Description:
 */
@Api(value = "UserController", description = "用户相关api")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录", notes = "根据用户名和密码登录")
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.getUser(username);
        User user1 = new User();
        String encryptedPassword = SHA256Str.getSHA256StrJava(password);
        if (user.getPassword().equals(encryptedPassword)) {
            user1.setUsername(username);
            user1.setPassword(password);
            user1.setRole(password);
            LoginInfo loginInfo = new LoginInfo(user1, JWTUtil.sign(username, encryptedPassword), "SUCCESS");
            return ResultGenerator.genSuccessResult(loginInfo);
        } else {
            return ResultGenerator.genFailResult("登录失败");
        }
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        if (userService.getUser(user.getUsername()) != null) {
            return ResultGenerator.genFailResult("用户已存在");
        }
        user.setPassword(SHA256Str.getSHA256StrJava(user.getPassword()));
        int id = userService.saveUser(user);
        return ResultGenerator.genSuccessResult(id);
    }

    @ApiOperation(value = "所有都可以访问")
    @GetMapping("/authAll")
    public Result authAll() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResultGenerator.genSuccessResult("You are already logged in");
        } else {
            return ResultGenerator.genSuccessResult("You are guest");
        }
    }

    @ApiOperation(value = "被认证可以访问")
    @GetMapping("/authed")
    @RequiresAuthentication
    public Result authed() {
        return ResultGenerator.genSuccessResult("You are authenticated");
    }

    @ApiOperation(value = "admin可以访问")
    @GetMapping("/authAdmin")
    @RequiresRoles("admin")
    public Result authAdmin() {
        return ResultGenerator.genSuccessResult("You are admin!");
    }

    @ApiOperation(value = "requirePermission可以访问")
    @GetMapping("/requirePermission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public Result requirePermission() {
        return ResultGenerator.genSuccessResult("You are visiting permission require edit,view!");
    }
}
