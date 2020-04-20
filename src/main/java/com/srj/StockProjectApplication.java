package com.srj;

import com.srj.common.spring.utils.SpringContextHolder;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.web.context.request.RequestContextListener;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
@EnableCaching
@ComponentScan({"com.srj.web.sys","com.srj.common.interceptor","com.srj.common.filter","com.srj.common.config"})
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
