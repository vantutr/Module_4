package com.codegym.thuchanh2.configuration;

import com.codegym.thuchanh2.jwt.CustomAccessDeniedHandler;
import com.codegym.thuchanh2.jwt.JwtAuthenticationTokenFilter;
import com.codegym.thuchanh2.jwt.RestAuthenticationEntryPoint;
import com.codegym.thuchanh2.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // Cung cấp PasswordEncoder để mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    // Cung cấp AuthenticationManager theo cách mới
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // AuthenticationProvider thay thế cho cấu hình AuthenticationManagerBuilder
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // JWT Filter
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(); // Khởi tạo filter đơn giản
    }

    // EntryPoint xử lý khi chưa xác thực
    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    // Handler xử lý khi bị từ chối truy cập
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    // Cấu hình chính thay thế cho configure(HttpSecurity)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationTokenFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**")) // Tắt CSRF cho /api/**
                .httpBasic(httpBasic -> httpBasic
                        .authenticationEntryPoint(restAuthenticationEntryPoint())
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(customAccessDeniedHandler())
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults());

        return http.build();
    }
}
