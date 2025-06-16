package com.codegym.thuchanh5.configuration;

import com.codegym.thuchanh5.service.CustomerService;
import com.codegym.thuchanh5.service.impl.SimpleCustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.codegym.thuchanh5")
public class AppConfiguration implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/customers/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public CustomerService customerService() {
        return new SimpleCustomerServiceImpl();
    }
}
