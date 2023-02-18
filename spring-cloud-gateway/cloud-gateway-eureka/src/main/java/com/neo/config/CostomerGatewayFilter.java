package com.neo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 参考博客
 * https://www.cnblogs.com/cy-e/p/15473518.html
 * https://mp.weixin.qq.com/s/QMYcgGzzF_qdb5F1LlI16A
 * https://blog.51cto.com/u_15753094/5830446
 */
@Slf4j
public class CostomerGatewayFilter implements GatewayFilter, Ordered {

    private static final String TIME = "time";
    private static final String USER_ID = "userId";
    private static final String APP_ID = "appId";

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        log.info("自定义局部过滤器：{}====================", "CustomerGatewayFilter");
//        return chain.filter(exchange);
//    }

    /**
     * 如何区分“pre”和“post”？
     * <p>
     * pre 就是 chain.filter(exchange)部分.
     * post 就是 then()部分.
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("CostomerGatewayFilter 前置操作===================");
        // 在放行 chain.filter(exchange) 之前，都是前置操作
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 设置请求头
        ServerHttpRequest serverHttpRequest = request.mutate().headers(httpHeaders -> {
            httpHeaders.add(USER_ID, UUID.randomUUID().toString());
            httpHeaders.add(APP_ID, "cloud");
        }).build();
        // 重置请求
        ServerWebExchange serverWebExchange = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(serverWebExchange)
                // .then 后置操作
                .then(
                        Mono.fromRunnable(() -> {
                            log.info("CostomerGatewayFilter 后置操作===================");
                            Long start = exchange.getAttribute(TIME);
                            if (start != null) {
                                log.info("exchange request uri:" + exchange.getRequest().getURI() + ", Time:" + (System.currentTimeMillis() - start) + "ms");
                            }
                        })
                );
    }


    /**
     * 值越小，优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;

        // 最小优先级
//        return Ordered.LOWEST_PRECEDENCE;
        // 最大优先级
//        return Ordered.HIGHEST_PRECEDENCE;
    }
}