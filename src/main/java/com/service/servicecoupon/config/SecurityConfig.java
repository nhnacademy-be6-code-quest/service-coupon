package com.service.servicecoupon.config;


import com.service.servicecoupon.filter.HeaderFilter;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    @Bean
    @SuppressWarnings("java:S4502") // Be sure to disable csrf
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
            .authorizeHttpRequests(req -> req.anyRequest().permitAll())
            .sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(new HeaderFilter(List.of(
                new HeaderFilter.RouteConfig(URI.create("/api/coupon"), HttpMethod.GET.name(), Collections.emptyList()),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/myPage"),
                    HttpMethod.GET.name(), Collections.emptyList()),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/register/**"), HttpMethod.GET.name(), List.of(ADMIN_ROLE)),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/payment"),
                    HttpMethod.PUT.name(), Collections.emptyList()),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/adminPage"),
                    HttpMethod.GET.name(), List.of(ADMIN_ROLE)),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/refund"),
                    HttpMethod.PUT.name(), Collections.emptyList()),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/payment/reward"),
                    HttpMethod.POST.name(), Collections.emptyList()),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/policy"),
                    HttpMethod.GET.name(), List.of(ADMIN_ROLE)),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/policy/register"),
                    HttpMethod.POST.name(), List.of(ADMIN_ROLE)),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/policy/type/**"),
                    HttpMethod.GET.name(), List.of(ADMIN_ROLE)),
                new HeaderFilter.RouteConfig(URI.create("/api/coupon/type"), HttpMethod.GET.name(),
                    List.of(ADMIN_ROLE))
            )
            ), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
