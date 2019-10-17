package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName GoodsApplication
 * @Description 商品微服务工程
 * @Author 传智播客
 * @Date 11:29 2019/10/16
 * @Version 2.1
 **/
@SpringBootApplication
@EnableEurekaClient     // eureka的客户端
@MapperScan(basePackages = {"com.changgou.goods.dao"})
public class GoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
