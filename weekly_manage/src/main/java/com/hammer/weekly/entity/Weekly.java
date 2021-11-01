package com.hammer.weekly.entity;

import java.util.Date;

public class Weekly {
    private int id;
    private int owner_id;
    private String owner_name;
    private String study_progress;
    private String daily_progress;
    private int total_time;
    private String time_allocation;
    private String experience;
    private Date post_time;
    private String comment;
    private Date judge_time;

    @Override
    public String toString() {
        return "Weekly{" +
                "id=" + id +
                ", owner_id=" + owner_id +
                ", owner_name='" + owner_name + '\'' +
                ", study_progress='" + study_progress + '\'' +
                ", daily_progress='" + daily_progress + '\'' +
                ", total_time=" + total_time +
                ", time_allocation='" + time_allocation + '\'' +
                ", experience='" + experience + '\'' +
                ", post_time=" + post_time +
                ", comment='" + comment + '\'' +
                ", judge_time=" + judge_time +
                '}';
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getJudge_time() {
        return judge_time;
    }

    public void setJudge_time(Date judge_time) {
        this.judge_time = judge_time;
    }

    public Date getPost_time() {
        return post_time;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getStudy_progress() {
        return study_progress;
    }

    public void setStudy_progress(String study_progress) {
        this.study_progress = study_progress;
    }

    public String getDaily_progress() {
        return daily_progress;
    }

    public void setDaily_progress(String daily_progress) {
        this.daily_progress = daily_progress;
    }

    public int getTotal_time() {
        return total_time;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }

    public String getTime_allocation() {
        return time_allocation;
    }

    public void setTime_allocation(String time_allocation) {
        this.time_allocation = time_allocation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

}
