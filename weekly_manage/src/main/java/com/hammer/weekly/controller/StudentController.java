package com.hammer.weekly.controller;


import com.alibaba.fastjson.JSONObject;
import com.hammer.weekly.entity.Student;
import com.hammer.weekly.entity.Weekly;
import com.hammer.weekly.service.Impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;


@RestController

public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    //判断是否登录成功
    @ResponseBody
    @RequestMapping(value = "/student/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req, HttpSession session) {

        JSONObject jsonObject = new JSONObject();

        String study_number = req.getParameter("study_number");
        String password = req.getParameter("password");

        int res = studentService.verifyInfo(study_number, password);

        if (res!=0) {

            jsonObject.put("code", 1);
            jsonObject.put("msg", "登录成功");

            int id = studentService.getId(study_number);
            String name = studentService.getName(study_number);

            jsonObject.put("name",name);
            jsonObject.put("id",id);

            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "用户名或密码错误");
            return jsonObject;
        }
    }

    //更改密码
    @ResponseBody
    @RequestMapping(value = "/modifypass", method = RequestMethod.POST)
    public Object modifyPass(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id");
        String newPass = req.getParameter("newPass");
        int res = studentService.modifyPass(Integer.parseInt(id),newPass);

        if(res !=0){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "更新密码成功");
            return jsonObject;
        }else{
            jsonObject.put("code", 0);
            jsonObject.put("msg", "更新密码失败");
            return jsonObject;
        }

    }

    //获取学生列表
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Object allStudent(){
        return studentService.getStudent();
    }

    //添加学生
    @ResponseBody
    @RequestMapping(value = "/addstudent",method = RequestMethod.POST)
    public Object postWeekly(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String study_number = req.getParameter("study_number").trim();
        String name = req.getParameter("name").trim();
        String password = req.getParameter("password").trim();

        Student student = new Student();
        student.setStudy_number(study_number);
        student.setName(name);
        student.setPassword(password);

        boolean res = studentService.addStudent(student)>0 ?true:false;
        if(res){
            //提交成功
            jsonObject.put("code",1);
            jsonObject.put("msg","添加成功");
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","添加失败");
            return jsonObject;
        }
    }

    //删除学生
    @RequestMapping(value = "/deletestudent",method = RequestMethod.GET)
    public Object deleteStudent(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();

        String id = req.getParameter("id");
        int res =  studentService.deleteStudentById(Integer.parseInt(id));
        if(res==1){
            //成功
            jsonObject.put("code",1);
            return jsonObject;
        }else {
            //失败
            jsonObject.put("code",0);
            return jsonObject;
        }
    }
}
