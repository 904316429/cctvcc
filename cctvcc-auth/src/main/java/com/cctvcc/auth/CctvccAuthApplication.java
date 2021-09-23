package com.cctvcc.auth;

import cn.cctvcc.security.annotation.EnableCctvFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-18 15:17
 */

@EnableDiscoveryClient
@EnableCctvFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CctvccAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CctvccAuthApplication.class, args);
    }
}
