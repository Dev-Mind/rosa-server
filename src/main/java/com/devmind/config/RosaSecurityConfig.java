package com.devmind.config;

import javax.servlet.Filter;

import com.devmind.security.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RosaSecurityConfig {
    @Bean
    public Filter securityFilter() {
        return new AuthenticationFilter()
                .addPathPatterns(
                        "/app/**/*",
                        "/monitoring/**/*"
                )
                .excludePathPatterns(
                        "/app/login",
                        "/app/account/check");
    }
}
