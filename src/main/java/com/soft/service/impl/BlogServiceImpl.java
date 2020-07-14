package com.soft.service.impl;

import com.soft.common.Page;
import com.soft.entity.Blog;
import com.soft.mapper.BlogMapper;
import com.soft.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getById(String id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Blog blog) {
        if (blog.getId() == null){
            blogMapper.insert(blog);
        } else {
            blogMapper.updateByPrimaryKey(blog);
        }
    }

    @Override
    public Page page(Integer currentPage, Integer length) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (length == null || length < 1) {
            length = 10;
        }
        int start = (currentPage - 1) * length;
        int dataCount = blogMapper.selectCount();
        List<Blog> blogList = blogMapper.selectByPage(start, length);

        int pages = dataCount%length.intValue() == 0 ? dataCount/length.intValue() : ((dataCount/length.intValue()) + 1);
        return new Page(blogList, dataCount, length, currentPage, new ArrayList<>(), true, pages);
    }
}
