package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author guojianfeng.
 * @Date Created in  2019/2/1
 * @Description：
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ClientAApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientAApplication.class,args);
    }
}
