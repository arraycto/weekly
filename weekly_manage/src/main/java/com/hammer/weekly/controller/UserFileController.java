package com.hammer.weekly.controller;

import com.alibaba.fastjson.JSONObject;
import com.hammer.weekly.entity.UserFile;
import com.hammer.weekly.service.Impl.UserFileServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class UserFileController {

    @Autowired
    private UserFileServiceImpl userFileService;



    //上传文件
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Object upload(@RequestParam("file") MultipartFile file, @RequestParam("id")int id, @RequestParam("name")String name, HttpServletRequest req) throws IOException {

        JSONObject jsonObject = new JSONObject();
        String old_name = file.getOriginalFilename();
        String ext = "."+FilenameUtils.getExtension(file.getOriginalFilename());

        String new_name = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "") + ext;

        Long size = file.getSize();

        String type = file.getContentType();

        //处理根据日期生成目录
        String real_path = ResourceUtils.getURL("classpath:").getPath() + "/static/files";
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath = real_path + "/" +dateFormat;
        File dateDir = new File(dateDirPath);
        if(!dateDir.exists()){
            dateDir.mkdirs();
        }

        //处理文件上传
        file.transferTo(new File(dateDir,new_name));

        //将文件放入数据库
        UserFile userFile = new UserFile();
        userFile.setOwner_id(id);
        userFile.setOwner_name(name);
        userFile.setOld_name(old_name);
        userFile.setNew_name(new_name);
        userFile.setExt(ext);
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        userFile.setSize(df.format((float)size/1024/1024));
        userFile.setType(type);
        userFile.setPath("/files/"+dateFormat);
        userFile.setPost_time(new Date());


        int res = userFileService.save(userFile);
        if(res!=0){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "上传成功");
            return jsonObject;
        }else{
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败");
            return jsonObject;
        }
    }

    //下载文件
    @GetMapping("download")
    public String download(@RequestParam("id")int id, HttpServletResponse res) throws IOException {
        //获取下载文件信息
        UserFile userFile = userFileService.findFileById(id);
        //根据文件名字和文件存储路径获取文件输入流
        String real_path = ResourceUtils.getURL("classpath:").getPath() + "/static" + userFile.getPath();

        FileInputStream fileInputStream = new FileInputStream(new File(real_path,userFile.getNew_name()));
        //下载
        res.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(userFile.getOld_name(), "UTF-8"));
        ServletOutputStream outputStream = res.getOutputStream();

        //文件拷贝
        IOUtils.copy(fileInputStream,outputStream);
        IOUtils.closeQuietly(fileInputStream);
        IOUtils.closeQuietly(outputStream);
        return "下载成功";
    }


    //获取文件列表

    @RequestMapping(value = "/getfile",method = RequestMethod.GET)
    public Object getFile(HttpServletRequest req){
        return userFileService.getFile();
    }
}
