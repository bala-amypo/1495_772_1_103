package com.example.demo.config;

import com.example.demo.servlet.DemoServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<DemoServlet> demoServlet() {
        ServletRegistrationBean<DemoServlet> bean =
                new ServletRegistrationBean<>(new DemoServlet(), "/demo");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
