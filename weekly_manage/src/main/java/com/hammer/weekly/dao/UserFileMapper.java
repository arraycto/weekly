package com.hammer.weekly.dao;

import com.hammer.weekly.entity.UserFile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFileMapper {
    int save(UserFile userFile);
    List<UserFile> getFile();
    UserFile findFileById(int id);
}
