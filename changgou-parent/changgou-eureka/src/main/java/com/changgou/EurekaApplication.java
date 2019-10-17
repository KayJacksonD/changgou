package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName com.changgou.EurekaApplication
 * @Description Eureka注册中心
 * @Author 传智播客
 * @Date 11:09 2019/10/16
 * @Version 2.1
 **/
@SpringBootApplication
@EnableEurekaServer         // 注册中心
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
