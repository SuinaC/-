package com.jia.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/***
 * @title FrontApplication
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/9 11:12
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class FrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class,args);
    }
}
