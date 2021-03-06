package tech.nautilus.brewery.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;

@Profile("google")
@Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator googleRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("google",
                        r -> r.path("/googlesearch")
                                .filters(f -> f.rewritePath("/googlesearch(?<segment>/.*)", "/${segment}").
                                        redirect(HttpStatus.FOUND.value(), "https://www.google.com/"))
                                .uri("https://www.google.com/"))
                .build();
    }
}
