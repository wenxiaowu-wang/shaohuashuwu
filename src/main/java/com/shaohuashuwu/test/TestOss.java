package com.shaohuashuwu.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestOss {
    public static void main(String[] args) throws FileNotFoundException {
       // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAI4GHgm8Qv5PCwSGBMNMYT";
        String accessKeySecret = "pMKax279ohKSOJEJoJTcNGDZCr6eA6";

// 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = new FileInputStream("C:\\Users\\耿建强\\Desktop\\bookpic\\qqqqqqqqqqqqqq.jpg");
        ossClient.putObject("shaohuashuwu", "handle/qqqqqqqwwwwwwwwwwwwwwqqqqqqqq.jpg", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
