package com.hammer.weekly.entity;

import java.util.Date;

public class UserFile {
    private int id;
    private int owner_id;
    private String owner_name;
    private String old_name;
    private String new_name;
    private String ext;
    private String path;
    private String size;
    private String type;
    private Date post_time;

    @Override
    public String toString() {
        return "UserFile{" +
                "id=" + id +
                ", owner_id=" + owner_id +
                ", owner_name='" + owner_name + '\'' +
                ", old_name='" + old_name + '\'' +
                ", new_name='" + new_name + '\'' +
                ", ext='" + ext + '\'' +
                ", path='" + path + '\'' +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", post_time=" + post_time +
                '}';
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOld_name() {
        return old_name;
    }

    public void setOld_name(String old_name) {
        this.old_name = old_name;
    }

    public String getNew_name() {
        return new_name;
    }

    public void setNew_name(String new_name) {
        this.new_name = new_name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getPost_time() {
        return post_time;
    }

    public void setPost_time(Date post_time) {
        this.post_time = post_time;
    }

}
