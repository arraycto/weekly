package com.hammer.weekly.service;


import com.hammer.weekly.entity.Student;

import java.util.List;

public interface StudentService {
    int verifyInfo(String study_number,String password);
    int getId(String study_number);
    String getName(String study_number);
    int modifyPass(int id,String newPass);
    List<Student> getStudent();
    int addStudent(Student student);
    int deleteStudentById(int id);
}
