package com.hammer.weekly.dao;

import com.hammer.weekly.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeMapper {
    List<Notice> getNotice();
    int postNotice(Notice notice);
    int deleteNoticeById(int id);
}
