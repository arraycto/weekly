package com.hammer.weekly.dao;


import com.hammer.weekly.entity.Student;
import com.hammer.weekly.entity.Weekly;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    int verifyInfo(String study_number,String password);
    int getId(String study_number);
    String getName(String study_number);
    int modifyPass(int id,String newPass);
    List<Student> getStudent();
    int addStudent(Student student);
    int deleteStudentById(int id);
}
