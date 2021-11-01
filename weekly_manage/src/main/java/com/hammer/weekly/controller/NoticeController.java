package com.hammer.weekly.controller;

import com.alibaba.fastjson.JSONObject;
import com.hammer.weekly.entity.Notice;

import com.hammer.weekly.service.Impl.NoticeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class NoticeController {
    @Autowired
    private NoticeServiceImpl noticeService;

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public Object allNotice(){
        return noticeService.getNotice();
    }

    //删除通知
    @RequestMapping(value = "/deletenotice",method = RequestMethod.GET)
    public Object findWeekly(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();

        String id = req.getParameter("id");
        int res =  noticeService.deleteNoticeById(Integer.parseInt(id));
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
    //发布通知
    @ResponseBody
    @RequestMapping(value = "/postnotice",method = RequestMethod.POST)
    public Object postNotice(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String title = req.getParameter("title").trim();
        String content = req.getParameter("content").trim();

        Notice notice = new Notice();

        notice.setTitle(title);
        notice.setContent(content);

        boolean res = noticeService.postNotice(notice)>0 ?true:false;
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
}
