package com.srj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.srj.web.sys")
public class StockProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockProjectApplication.class, args);
    }


}
