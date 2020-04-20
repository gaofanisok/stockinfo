package com.stockinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.stockinfo"})
public class StockinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockinfoApplication.class, args);
    }

}
