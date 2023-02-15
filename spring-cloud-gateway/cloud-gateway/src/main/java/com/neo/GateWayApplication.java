package com.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 对应博客
 * http://www.ityouknow.com/springcloud/2018/12/12/spring-cloud-gateway-start.html
 */
@SpringBootApplication
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		// http://localhost:8080/about -> http://www.ityouknow.com/about
		return builder.routes()
				.route("path_route",// id
						r -> r.path("/about")// predicates
								.uri("http://ityouknow.com")) // uri
				.build();
	}

}
