package com.changgou.Utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
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
    public static StorageServer getStoreStorage(String group_name) {
        StorageServer storeStorage = null;
        try {
            trackerServer = trackerClient.getConnection();
            storeStorage = trackerClient.getStoreStorage(trackerServer, group_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storeStorage;
    }
}
