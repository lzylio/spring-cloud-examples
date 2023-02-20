package com.neo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 继承的方法实现过滤器
 * 如果命名为 MyGatewayFilterFactory 会找不到过滤器，过滤器名字不能太短。。。
 *
 * 参考博客 https://www.cnblogs.com/westlin/p/10909799.html
 */
@Slf4j
@Component
public class MyTestGatewayFilterFactory extends AbstractGatewayFilterFactory<FilterFactoryConfig> {

    private static final String ENABLED = "enabled";

    private static final String NAME = "name";

    public MyTestGatewayFilterFactory() {
        super(FilterFactoryConfig.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(ENABLED, NAME);
    }

    @Override
    public GatewayFilter apply(FilterFactoryConfig config) {
        return ((exchange, chain) -> {
            if (log.isDebugEnabled()) {
                log.debug("apply() - enabled={}", config.isEnabled());
            }
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }
            log.info(ENABLED + ":" + config.isEnabled());
            log.info(NAME + ":" + config.getName());
            return chain.filter(exchange);
        });
    }
}
