package com.neo.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

/**
 * 在配置文件中使用自定义局部过滤器还需要使用自定义过滤器工厂来包装
 * 这里的后缀GatewayFilterFactory不能写错，因为配置文件中配置的自定义过滤器名就是自定义过滤器工厂的类名去掉GatewayFilterFactory后缀的名字
 * 把后缀写错了项目启动的时候就会报错说找不到这个自定义过滤器
 */
public class CustomerGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return new CostomerGatewayFilter();
    }
}
