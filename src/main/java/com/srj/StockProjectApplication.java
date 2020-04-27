package com.srj;

import com.srj.common.spring.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.request.RequestContextListener;


@SpringBootApplication
@EnableCaching
@ComponentScan({"com.srj.web.sys","com.srj.web.datacenter","com.srj.web.tool","com.srj.common.interceptor","com.srj.common.config"})
public class StockProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockProjectApplication.class, args);
        //此为打印所有bean的方法
        /*ApplicationContext context = SpringApplication.run(StockProjectApplication.class, args);
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);*/
    }




    /**
     * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
     * 使用方式。在启动类里添加Bean
     * */
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    /**
     * 不可或缺*/
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }
    /**
     * Filter拦截器组*//*
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/*");
        registration.setName("LoginFilter");
        registration.setOrder(1);
        return registration;
    }*/
}
