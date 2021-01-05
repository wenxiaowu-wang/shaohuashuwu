package com.shaohuashuwu.controller;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/uploadCotroller")
public class UploadController {

    @Autowired
    private OSSClient ossClient;

    @ResponseBody
    @RequestMapping("/oss")
    public String ossUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id1");
        int work_id=Integer.parseInt(String.valueOf(msg));
        String work_id_name =  Integer.toString(work_id);
        String bucketName = "shaohuashuwu";
        String fileName = "handle/"+work_id_name+".jpg";
        ossClient.putObject(bucketName,fileName,file.getInputStream());

        String route = "https://"+bucketName+".oss-cn-beijing.aliyuncs.com/"+fileName;
        System.out.println("返回网址"+route);

        return route;

    }

}
