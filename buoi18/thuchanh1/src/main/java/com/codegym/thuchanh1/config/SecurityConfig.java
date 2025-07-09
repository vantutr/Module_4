package com.codegym.thuchanh1.config;

import com.codegym.thuchanh1.rest.CustomAccessDeniedHandler;
import com.codegym.thuchanh1.rest.JwtAuthenticationTokenFilter;
import com.codegym.thuchanh1.rest.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    // Cấu hình AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Cấu hình filter JWT
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    // Cấu hình entry point khi chưa đăng nhập
    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    // Cấu hình khi bị từ chối truy cập
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    // Cấu hình SecurityFilterChain thay thế WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthenticationTokenFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/rest/**"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/rest/login**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/rest/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/rest/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/rest/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(restAuthenticationEntryPoint())
                        .accessDeniedHandler(customAccessDeniedHandler())
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
