package com.neusoft.managerweb.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class MyUploadUtil {

    public static String uploadImage(MultipartFile file){
        //配置fdfs的全局信息
        String path = MyUploadUtil.class.getClassLoader().getResource("tracker.conf").getFile();
        String[] upload_file = new String[0];
        try {
            ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        //获得tracker
        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer= null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通过tracker获得storage
        StorageClient storageClient=new StorageClient(trackerServer,null);
        //通过最后一个点获取扩展名
        String originalFilename = file.getOriginalFilename();
        int l = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(l+1);
        try {
            //通过storage上传文件
            upload_file = storageClient.upload_file(file.getBytes(), substring, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        String url="http://172.16.13.250";
        for (int i = 0; i < upload_file.length; i++) {
            url =url+"/"+ upload_file[i];
        }
        return url;
    }
}
