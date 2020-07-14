package com.soft.mapper;

import com.soft.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    User selectByPrimaryKey(String id);

    User selectByUserName(String username);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}