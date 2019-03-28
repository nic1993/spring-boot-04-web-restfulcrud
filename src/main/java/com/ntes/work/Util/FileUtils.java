package com.ntes.work.Util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传工具包
 */
public class FileUtils {

    static int UPLOAD_STATUS_SUCCFESS = 1;
    static int UPLOAD_STATUS_FAIL = 2;

    static  String SUCCFESS_MSG = "上传成功!";
    static  String FAIL_MSG = "上传失败!";

    /**
     *
     * @param file 文件
     * @param fileName 源文件名
     * @return
     */
    public static Map upload(MultipartFile file, String fileName,HashMap map){
        // 生成新的文件名
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
    }


}
