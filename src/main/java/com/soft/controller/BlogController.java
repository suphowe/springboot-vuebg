package com.soft.controller;

import cn.hutool.core.bean.BeanUtil;
import com.soft.common.Page;
import com.soft.entity.Blog;
import com.soft.service.BlogService;
import com.soft.sys.Result;
import com.soft.util.DateUtil;
import com.soft.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public String blogs(Integer currentPage, Integer length) {
        Page pageData = blogService.page(currentPage, length);
        return Result.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public String detail(@PathVariable(name = "id") String id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        return Result.succ(blog);
    }

    @PostMapping("/blog/edit")
    public String edit(@Validated @RequestBody Blog blog) {
        System.out.println(blog.toString());
        Blog temp = null;
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().equals(blog.getUserId()), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(blog.getUserId());
            temp.setCreated(DateUtil.localDateTimeToDate(LocalDateTime.now()));
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return Result.succ(200, "操作成功", null);
    }
}
