package com.cctvcc.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-18 15:17
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CctvccAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CctvccAuthApplication.class, args);
    }
}
