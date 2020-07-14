package com.soft.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.soft.dto.LoginDto;
import com.soft.entity.User;
import com.soft.service.UserService;
import com.soft.sys.Result;
import com.soft.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    /**
     * 默认账号密码：admin / 111111
     *
     */
    @CrossOrigin
    @PostMapping("/login")
    public String login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(loginDto.getUsername());
        Assert.notNull(user, "用户不存在");
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误！");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 用户可以另一个接口
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication             //登录之后才能访问的接口
    public String logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
