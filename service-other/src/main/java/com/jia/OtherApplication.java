package com.jia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/***
 * @title OtherApplication
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/9 11:14
 **/
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.jia.mapper")
public class OtherApplication {
    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class,args);
    }
}
