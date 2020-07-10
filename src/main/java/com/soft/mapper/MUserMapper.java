package com.soft.mapper;

import com.soft.entity.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(MUser record);

    MUser selectByPrimaryKey(String id);

    List<MUser> selectAll();

    int updateByPrimaryKey(MUser record);
}