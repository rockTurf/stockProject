package com.srj.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义静态资源映射
 * */
@Configuration
public class CustomerMvcConfigurerAdapter implements WebMvcConfigurer {
    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //如下配置则能可以访问src/main/resources/WEB-INF/static下面的文件
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //如访问WEB-INF/static文件夹下的a.jpg，则输入：localhost:8080/WEB-INF/static/a.jpg
    }

}
