package com.changgou.Utils;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class StorageUtil {
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageClient storageClient;
    static {
        try {
            ClientGlobal.init(new ClassPathResource("fdfs_client.conf").getPath());
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StorageClient getStorageClient() {
        return storageClient;
    }
    public static String getIp() {
        return trackerServer.getInetSocketAddress().getAddress().getHostAddress();
    }
    public static int getPort() {
        return ClientGlobal.getG_tracker_http_port();
    }
}
