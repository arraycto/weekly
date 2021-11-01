package com.hammer.weekly.service.Impl;

import com.hammer.weekly.dao.NoticeMapper;
import com.hammer.weekly.entity.Notice;
import com.hammer.weekly.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getNotice(){
        return noticeMapper.getNotice();
    }

    @Override
    public int postNotice(Notice notice){
        return noticeMapper.postNotice(notice);
    }

    @Override
    public int deleteNoticeById(int id){
        return noticeMapper.deleteNoticeById(id);
    }

}
