package com.hammer.weekly.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {
    int verifyInfo(String name,String password);
    int modifyPass(String password);
}
