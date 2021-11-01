package com.hammer.weekly.service.Impl;

import com.hammer.weekly.dao.WeeklyMapper;
import com.hammer.weekly.entity.Weekly;
import com.hammer.weekly.service.WeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WeeklyServiceImpl implements WeeklyService {
    @Autowired
    private WeeklyMapper weeklyMapper;

    @Override
    public List<Weekly> findWeeklyById(int id){
        return weeklyMapper.findWeeklyById(id);
    }

    @Override
    public int updateWeekly(Weekly weekly){
        return weeklyMapper.updateWeekly(weekly);
    }

    @Override
    public int postWeekly(Weekly weekly){
        return weeklyMapper.postWeekly(weekly);
    }

    @Override
    public List<Weekly> findTwoWeeksWeekly(){
        return weeklyMapper.findTwoWeeksWeekly();
    }

    @Override
    public int updateJudgementById(int id, String comment, Date judge_time){
        return weeklyMapper.updateJudgementById(id,comment,judge_time);
    };
}
