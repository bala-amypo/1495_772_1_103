package com.example.demo.config;

import com.example.demo.servlet.HelloServlet;
import com.example.demo.servlet.DemoServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<HelloServlet> helloServlet() {
        ServletRegistrationBean<HelloServlet> servletBean =
                new ServletRegistrationBean<>(new HelloServlet(), "/hello-servlet");
        servletBean.setLoadOnStartup(1);
        return servletBean;
    }

    @Bean
    public ServletRegistrationBean<DemoServlet> demoServlet() {
        ServletRegistrationBean<DemoServlet> servletBean =
                new ServletRegistrationBean<>(new DemoServlet(), "/demo");
        servletBean.setLoadOnStartup(1);
        return servletBean;
    }
}
