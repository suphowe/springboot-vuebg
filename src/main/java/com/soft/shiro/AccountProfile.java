package com.soft.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录成功之后返回的一个用户信息的载体
 * @author suphowe
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
    private String avatar;
}
