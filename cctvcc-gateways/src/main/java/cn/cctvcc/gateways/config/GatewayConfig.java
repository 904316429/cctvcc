//package cn.cctvcc.gateways.config;
//
//import cn.cctvcc.gateways.handler.SentinelFallbackHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//
///**
// * @description: 网管限流配置
// * @author: Jiang
// * @create: 2021-09-26 11:18
// */
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SentinelFallbackHandler sentinelGatewayExceptionHandler() {
//        return new SentinelFallbackHandler();
//    }
//
//}
