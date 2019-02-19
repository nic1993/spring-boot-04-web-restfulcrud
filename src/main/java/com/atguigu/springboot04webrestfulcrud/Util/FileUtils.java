package com.atguigu.springboot04webrestfulcrud.Util;

import com.atguigu.springboot04webrestfulcrud.config.MyMvcConfig;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传工具包
 */
public class FileUtils {


    static  String SUCCFESS_MSG = "上传成功!";
    static  String FAIL_MSG = "上传失败!";

    static String ROOT_PATH = "/NTES/asserts/temp/";

    static  String path = MyMvcConfig.TEMP_PATH;

    /**
     *
     * @param file 文件
     * @param fileName 源文件名
     * @return
     */
    public static Map upload(MultipartFile file, String fileName){
        // 生成新的文件名
        Map<String,Object> map = new HashMap<>();
        try {
            InputStream input = file.getInputStream();
            String newname = FileNameUtils.getFileName(fileName);
            FTPClient client = FtpUtils.getFtpClient();
            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.storeFile(newname,input);
            input.close();
            map.put("imgpath",FtpUtils.location + "/" + newname);
            map.put("msg",SUCCFESS_MSG);
            return  map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg",FAIL_MSG);
            return map;
        }
//        Map<String,Object> map = new HashMap<>();
//
//        String tempPath = path + "/" +  FileNameUtils.getFileName(fileName);
//
//        //使用原文件名
////      String realPath = path + "/" + fileName;
//
//        File dest = new File(tempPath);
//
//        //判断文件父目录是否存在
//        if(!dest.getParentFile().exists()){
//            dest.getParentFile().mkdirs();
//        }
//
//        try {
//            //保存文件
//            file.transferTo(dest);
//
//            map.put("imgpath",ROOT_PATH + "/" + dest.getName());
//            map.put("msg",SUCCFESS_MSG);
//            return map;
//        } catch (IllegalStateException e) {
//            // TODO Auto-generated catch block
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            map.put("msg",FAIL_MSG);
//            return map;
//        }

    }


}
