package com.hammer.weekly.service;

import com.hammer.weekly.entity.Weekly;

import java.util.Date;
import java.util.List;

public interface WeeklyService {
    List<Weekly> findWeeklyById(int id);
    int updateWeekly(Weekly weekly);
    int postWeekly(Weekly weekly);
    List<Weekly> findTwoWeeksWeekly();
    int updateJudgementById(int id, String comment, Date judge_time);
}
