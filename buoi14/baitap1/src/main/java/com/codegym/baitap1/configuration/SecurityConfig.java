package com.codegym.baitap1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean cung cấp thông tin người dùng.
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    // Bean cấu hình các quy tắc bảo mật
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        // Cho phép tất cả mọi người truy cập các trang này
                        .antMatchers("/", "/blogs", "/blogs/view/**").permitAll()
                        // Các trang yêu cầu vai trò ADMIN
                        .antMatchers("/categories/**").hasRole("ADMIN")
                        // Các trang viết, sửa, xóa blog yêu cầu đã đăng nhập (vai trò USER hoặc ADMIN)
                        .antMatchers("/blogs/create", "/blogs/edit/**", "/blogs/delete/**").hasAnyRole("USER", "ADMIN")
                        // Tất cả các request còn lại đều yêu cầu xác thực (đã đăng nhập)
                        .anyRequest().authenticated()
                )
                // Cấu hình form đăng nhập
                .formLogin(form -> form
                        .loginPage("/login") // Chỉ định URL của trang đăng nhập tùy chỉnh
                        .defaultSuccessUrl("/blogs", true) // URL sau khi đăng nhập thành công
                        .permitAll() // Cho phép tất cả mọi người truy cập trang đăng nhập
                )
                // Cấu hình đăng xuất
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để thực hiện đăng xuất
                        .logoutSuccessUrl("/blogs") // URL sau khi đăng xuất thành công
                        .permitAll()
                )
                // Cấu hình trang lỗi khi truy cập bị từ chối
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied")
                );
        return http.build();
    }

}
