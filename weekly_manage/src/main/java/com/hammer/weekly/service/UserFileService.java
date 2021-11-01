package com.hammer.weekly.service;


import com.hammer.weekly.entity.UserFile;

import java.util.List;

public interface UserFileService {
    int save(UserFile userFile);
    List<UserFile> getFile();
    UserFile findFileById(int id);
}
