package com.hammer.weekly.service.Impl;

import com.hammer.weekly.dao.TeacherMapper;
import com.hammer.weekly.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public int verifyInfo(String name,String password){
        return teacherMapper.verifyInfo(name,password);
    }

    @Override
    public int modifyPass(String password){
        return teacherMapper.modifyPass(password);
    };
}
