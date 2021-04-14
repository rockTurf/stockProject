package com.srj.common.interceptor;

import com.srj.common.handler.AdminInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration registration = registry.addInterceptor(new AdminInterceptor());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/login",            //登录
               "/register",
                "/hello",
                "/userRegist");
    }

}
