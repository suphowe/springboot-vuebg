package com.soft.controller;

import com.soft.entity.MUser;
import com.soft.service.IMUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置文件操作
 * @author suphowe
 */
@RestController
@RequestMapping("/User")
@Api(value = "配置文件操作")
public class MUserController {

    @Autowired
    IMUserService mUserService;

    @GetMapping("/{id}")
    public Object test(@PathVariable("id") String id) {
        MUser result = mUserService.getById(id);
        return result;
    }
}
