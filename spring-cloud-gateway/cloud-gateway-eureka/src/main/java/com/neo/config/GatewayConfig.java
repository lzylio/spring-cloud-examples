package com.neo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public CustomerGatewayFilterFactory myGatewayFilterFactory() {
        return new CustomerGatewayFilterFactory();
    }

}
