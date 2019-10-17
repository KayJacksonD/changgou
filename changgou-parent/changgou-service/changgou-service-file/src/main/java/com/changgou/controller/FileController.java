package com.changgou.controller;

import entity.Result;
import entity.StatusCode;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        try {
            ClientGlobal.init(new ClassPathResource("fdfs_client.conf").getPath());
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            String[] uploadReuslt = storageClient.upload_file(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename()), null);

            String ip = trackerServer.getInetSocketAddress().getAddress().getHostAddress(); // 服务器地址
            int port = ClientGlobal.getG_tracker_http_port();
            String url = "http://" + ip + ":" + port + "/";
            System.out.println(url);

            return new Result(true, StatusCode.OK, "上传文件成功！", uploadReuslt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/download")
    public Result download() {
        try {
            ClientGlobal.init(new ClassPathResource("fdfs_client.conf").getPath());
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            byte[] result = storageClient.download_file("group1", "M00/00/00/wKjThF2oMwiAADr9AAEIVmrHuhY185.PNG");
            IOUtils.write(result,new FileOutputStream(new File("D:\\test\\test.png")));
            return new Result(true, StatusCode.OK, "下载文件成功！", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
