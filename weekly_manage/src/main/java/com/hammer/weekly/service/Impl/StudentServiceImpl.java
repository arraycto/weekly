package com.hammer.weekly.service.Impl;

import com.hammer.weekly.dao.StudentMapper;
import com.hammer.weekly.entity.Student;
import com.hammer.weekly.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int verifyInfo(String study_number, String password) {

        return studentMapper.verifyInfo(study_number, password);
    }

    @Override
    public int getId(String study_number){
        return studentMapper.getId(study_number);
    }

    @Override
    public String getName(String study_number){
        return studentMapper.getName(study_number);
    }

    @Override
    public int modifyPass(int id,String newPass){
        return studentMapper.modifyPass(id,newPass);
    }

    @Override
    public List<Student> getStudent(){
        return studentMapper.getStudent();
    };

    @Override
    public int addStudent(Student student){
        return studentMapper.addStudent(student);
    };

    @Override
    public int deleteStudentById(int id){
        return studentMapper.deleteStudentById(id);
    };
}
