package com.soft.mapper;

import com.soft.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BlogMapper {

    int deleteByPrimaryKey(String id);

    int insert(Blog record);

    Blog selectByPrimaryKey(String id);

    List<Blog> selectAll();

    int updateByPrimaryKey(Blog record);

    List<Blog> selectByPage(int start, int length);

    int selectCount();
}