package com.hammer.weekly.service.Impl;

import com.hammer.weekly.dao.UserFileMapper;
import com.hammer.weekly.entity.UserFile;
import com.hammer.weekly.service.UserFileService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFileServiceImpl implements UserFileService {

    @Autowired
    private UserFileMapper userFileMapper;

    @Override
    public int save(UserFile userFile){
        return userFileMapper.save(userFile);
    }

    @Override
    public List<UserFile> getFile(){
        return userFileMapper.getFile();
    };

    @Override
    public UserFile findFileById(int id){
        return userFileMapper.findFileById(id);
    };
}
