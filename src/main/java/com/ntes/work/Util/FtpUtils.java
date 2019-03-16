package com.ntes.work.Util;

import com.ntes.work.exception.ConnectFailException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FtpUtils {

    private static String address;

    private static Integer port;

    private static String username;

    private static String password;

    private static String basepath;

    public static String location;

    private static FTPClient ftpClient;

    private static  String lock = "";

    public static String getAddress() {
        return address;
    }

    @Value("${ftp.address}")
    public void setAddress(String address) {
        FtpUtils.address = address;
    }
    public static Integer getPort() {
        return port;
    }
    @Value("${ftp.port}")
    public  void setPort(Integer port) {
        FtpUtils.port = port;
    }

    public static String getUsername() {
        return username;
    }
    @Value("${ftp.username}")
    public  void setUsername(String username) {
        FtpUtils.username = username;
    }

    public static String getPassword() {
        return password;
    }
    @Value("${ftp.password}")
    public  void setPassword(String password) {
        FtpUtils.password = password;
    }

    public static String getBasepath() {
        return basepath;
    }
    @Value("${ftp.basepath}")
    public  void setBasepath(String basepath) {
        FtpUtils.basepath = basepath;
    }

    public static String getLocation() {
        return location;
    }

    @Value("${ftp.location}")
    public  void setLocation(String location) {
        FtpUtils.location = location;
    }

    public  void setFtpClient(FTPClient ftpClient) {
        FtpUtils.ftpClient = ftpClient;
    }

    public static synchronized FTPClient getFtpClient() {
        if (ftpClient == null) {
            synchronized (lock) {
                if (ftpClient == null) {
                    ftpClient = new FTPClient();
                    int reply;
                    try {
//                        ftpClient.connect(address, port);
//                        ftpClient.login(username, password);
                        ftpClient.connect("101.132.149.10", 21);
                        ftpClient.login("ftpuser","cyk688388");
                        reply = ftpClient.getReplyCode();

                        if (!FTPReply.isPositiveCompletion(reply)) {
                            ftpClient.disconnect();
                            ftpClient = null;
                            throw new ConnectFailException();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ftpClient;
    }


}


