package cn.cctvcc.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-18 16:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CctvSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CctvSystemApplication.class, args);
    }
}
