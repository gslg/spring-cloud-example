package com.cloud.message;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Description:
 * @Author: lg@gridsum.com
 * @Date: 2018/8/16
 */
@SpringCloudApplication
public class MessageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApplication.class,args);
    }
}
