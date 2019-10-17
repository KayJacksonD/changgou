package com.changgou.controller;

import com.changgou.Utils.StorageUtil;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.StorageClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {
        try {
            StorageClient storageClient = StorageUtil.getStorageClient();
            String[] uploadReuslt = storageClient.upload_file(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            String ip = StorageUtil.getIp(); // 服务器地址
            int port = StorageUtil.getPort();
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
            StorageClient storageClient = StorageUtil.getStorageClient();
            byte[] result = storageClient.download_file("group1", "M00/00/00/wKjThF2oVM6ATFkAAAN7iIiMniY274.jpg");
            IOUtils.write(result, new FileOutputStream(new File("D:\\test\\test.png")));
            return new Result(true, StatusCode.OK, "下载文件成功！", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/delete")
    public Result delete() {
        try {
            StorageClient storageClient = StorageUtil.getStorageClient();
            storageClient.delete_file("group1", "M00/00/00/wKjThF2oVM6ATFkAAAN7iIiMniY274.jpg");
            return new Result(true, StatusCode.OK, "删除文件成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, StatusCode.ERROR, "删除文件失败！");
        }

    }
}
