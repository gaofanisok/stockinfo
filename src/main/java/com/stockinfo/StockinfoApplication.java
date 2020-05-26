package com.stockinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.stockinfo.dao")
@ComponentScan(basePackages = {"com.stockinfo"})
public class StockinfoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StockinfoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(StockinfoApplication.class, args);
    }

}
