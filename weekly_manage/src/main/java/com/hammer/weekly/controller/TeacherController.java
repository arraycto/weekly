package com.hammer.weekly.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hammer.weekly.service.Impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;

    @ResponseBody
    @RequestMapping(value = "/teacher/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String name = req.getParameter("name").trim();
        String password = req.getParameter("password").trim();
        int res = teacherService.verifyInfo(name,password);
        if(res!=0){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("name",name);
            return jsonObject;
        }else{
            jsonObject.put("code", 0);
            jsonObject.put("msg", "用户名或密码错误");
            return jsonObject;
        }
    }

    //更改密码
    @ResponseBody
    @RequestMapping(value = "/modifyteacherpass", method = RequestMethod.POST)
    public Object modifyPass(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String newPass = req.getParameter("newPass");
        int res = teacherService.modifyPass(newPass);

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
}
