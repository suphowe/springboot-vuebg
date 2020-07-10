package com.soft.service.impl;

import com.soft.entity.MUser;
import com.soft.mapper.MUserMapper;
import com.soft.service.IMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MUserServiceImpl implements IMUserService {

    @Autowired
    private MUserMapper mUserMapper;

    @Override
    public MUser getById(String id){
        return mUserMapper.selectByPrimaryKey(id);
    }
}
