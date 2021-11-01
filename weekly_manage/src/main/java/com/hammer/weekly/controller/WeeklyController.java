package com.hammer.weekly.controller;

import com.alibaba.fastjson.JSONObject;
import com.hammer.weekly.entity.Weekly;
import com.hammer.weekly.service.Impl.WeeklyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class WeeklyController {

    @Autowired
    private WeeklyServiceImpl weeklyService;



    //获取特定学生的周报
    @RequestMapping(value = "/getweekly",method = RequestMethod.GET)
    public Object findWeeklyById(HttpServletRequest req){
        String id = req.getParameter("id");
        return weeklyService.findWeeklyById(Integer.parseInt(id));
    }

    //获取过去两周的周报
    @RequestMapping(value = "/gettwoweeksweekly",method = RequestMethod.GET)
    public Object findUnapprovedWeekly(HttpServletRequest req){
        return weeklyService.findTwoWeeksWeekly();
    }
    //更新周报
    @ResponseBody
    @RequestMapping(value = "/updateweekly",method = RequestMethod.POST)
    public Object updateWeekly(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String study_progress = req.getParameter("study_progress").trim();
        String daily_progress = req.getParameter("daily_progress").trim();
        String total_time = req.getParameter("total_time").trim();
        String time_allocation = req.getParameter("time_allocation").trim();
        String experience = req.getParameter("experience").trim();

        /*前端已做判空，所以这里不用判空*/

        Weekly weekly = new Weekly();

        weekly.setId(Integer.parseInt(id));
        weekly.setStudy_progress(study_progress);
        weekly.setDaily_progress(daily_progress);
        weekly.setTime_allocation(time_allocation);
        weekly.setTotal_time(Integer.parseInt(total_time));
        weekly.setExperience(experience);
        weekly.setPost_time(new Date());

        int res = weeklyService.updateWeekly(weekly);
        if(res == 1){
            //更新成功
            jsonObject.put("code",1);
            jsonObject.put("msg","修改成功");
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","修改失败");
            return jsonObject;
        }
    }

    //发布周报
    @ResponseBody
    @RequestMapping(value = "/postweekly",method = RequestMethod.POST)
    public Object postWeekly(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String owner_id = req.getParameter("owner_id").trim();
        String owner_name = req.getParameter("owner_name").trim();
        String study_progress = req.getParameter("study_progress").trim();
        String daily_progress = req.getParameter("daily_progress").trim();
        String total_time = req.getParameter("total_time").trim();
        String time_allocation = req.getParameter("time_allocation").trim();
        String experience = req.getParameter("experience").trim();

        Weekly weekly = new Weekly();

        weekly.setOwner_id(Integer.parseInt(owner_id));
        weekly.setOwner_name(owner_name);
        weekly.setStudy_progress(study_progress);
        weekly.setDaily_progress(daily_progress);
        weekly.setTime_allocation(time_allocation);
        weekly.setTotal_time(Integer.parseInt(total_time));
        weekly.setExperience(experience);
        weekly.setPost_time(new Date());

        boolean res = weeklyService.postWeekly(weekly)>0 ?true:false;
        if(res){
            //提交成功
            jsonObject.put("code",1);
            jsonObject.put("msg","提交成功");
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","提交失败");
            return jsonObject;
        }
    }

    //保存审阅
    @ResponseBody
    @RequestMapping(value = "/updatejudgement",method = RequestMethod.POST)
    public Object updateJudgement(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String comment = req.getParameter("comment").trim();
        Date date = new Date();

        int res = weeklyService.updateJudgementById(Integer.parseInt(id),comment,date);
        if(res==1){
            //提交成功
            jsonObject.put("code",1);
            jsonObject.put("msg","审阅成功");
            return jsonObject;
        }else{
            jsonObject.put("code",0);
            jsonObject.put("msg","审阅失败");
            return jsonObject;
        }

    }

}
