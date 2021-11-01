package com.hammer.weekly.dao;

import com.hammer.weekly.entity.Weekly;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeeklyMapper {
    List<Weekly> findWeeklyById(int id);
    int updateWeekly(Weekly weekly);
    int postWeekly(Weekly weekly);
    List<Weekly> findTwoWeeksWeekly();
    int updateJudgementById(int id, String comment, Date judge_time);
}
