package cn.cctvcc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-26 12:23
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CctvccGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CctvccGatewayApplication.class, args);
    }

}
