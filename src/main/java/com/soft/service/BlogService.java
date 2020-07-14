package com.soft.service;

import com.soft.common.Page;
import com.soft.entity.Blog;

public interface BlogService {

    Blog getById(String id);

    void saveOrUpdate(Blog blog);

    Page page(Integer currentPage, Integer length);
}
