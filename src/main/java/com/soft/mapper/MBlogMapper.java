package com.soft.mapper;

import com.soft.entity.MBlog;
import java.util.List;

public interface MBlogMapper {
    int deleteByPrimaryKey(String id);

    int insert(MBlog record);

    MBlog selectByPrimaryKey(String id);

    List<MBlog> selectAll();

    int updateByPrimaryKey(MBlog record);
}