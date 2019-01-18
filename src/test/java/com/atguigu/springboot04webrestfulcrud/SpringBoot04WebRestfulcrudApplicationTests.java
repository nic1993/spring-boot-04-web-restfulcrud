package com.atguigu.springboot04webrestfulcrud;

import com.atguigu.springboot04webrestfulcrud.entities.Buyer;
import com.atguigu.springboot04webrestfulcrud.entities.Goods;
import com.atguigu.springboot04webrestfulcrud.mapper.GoodsMapper;
import com.atguigu.springboot04webrestfulcrud.mapper.Loginmapper;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot04WebRestfulcrudApplicationTests {

	@Autowired
    Loginmapper loginmapper;

	@Autowired
	GoodsMapper goodsMapper;

	@Test
	public void contextLoads() {
		Buyer buyer =  loginmapper.getBuyerByName("buyer");
		System.out.println(buyer.getName() + " " + buyer.getPassword());
	}


	@Test
	public  void test(){

        /* ClassPathResource resource = new ClassPathResource(path + "/bootstrap-solid.svg"); */
        try {
            //获取跟目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            System.out.println("path:"+path.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
            File upload = new File(path.getAbsolutePath(),"src/main/resources/static/asserts/img/");
            if(!upload.exists()) upload.mkdirs();
            System.out.println("upload url:"+upload.getAbsolutePath());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}

	@Test
	public void testftp()
	{


		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect("101.132.149.10", 21);
			ftp.login("ftpuser","cyk688388");
			reply = ftp.getReplyCode();

			System.out.println("reply"+ reply);
			if(!FTPReply.isPositiveCompletion(reply)){
				ftp.disconnect();
			}

			File file = ResourceUtils.getFile("classpath:static/asserts/img/2018.jpg");
			FileInputStream input = new FileInputStream(file);


			System.out.println(ftp.changeWorkingDirectory("/home/images"));
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);

			System.out.println(ftp.storeFile("2018.jpg",input));
			input.close();



		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
