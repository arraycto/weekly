package com.hammer.weekly.service;

import com.hammer.weekly.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getNotice();
    int postNotice(Notice notice);
    int deleteNoticeById(int id);
}
