package com.codegym.baitap1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean để mã hóa mật khẩu. Sử dụng BCrypt là một chuẩn an toàn.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean để quản lý thông tin người dùng.
     * Ở đây, chúng ta tạo một người dùng trong bộ nhớ để đơn giản hóa.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("client")
                .password(passwordEncoder().encode("password123")) // Mật khẩu là "password123"
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Bean để cấu hình chuỗi filter bảo mật.
     *
     * @param http Đối tượng HttpSecurity để cấu hình.
     * @return SecurityFilterChain đã được cấu hình.
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Yêu cầu tất cả các request đều phải được xác thực
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                // Kích hoạt xác thực qua HTTP Basic
                .httpBasic(Customizer.withDefaults())
                // Vô hiệu hóa CSRF vì đây là một API stateless
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}