package com.coral.springboot.config;

import com.coral.springboot.component.LoginHandleInterceptor;
import com.coral.springboot.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置器
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //对首页增加几种方式，使/ 和 /index.html 都可以跳转到login页面
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        //重定向 防止重复提交/user/login
        registry.addViewController(("/main.html")).setViewName("dashboard");
    }
   /*@Bean
   public WebMvcConfigurer webMvcConfigurer(){
       WebMvcConfigurer adapter = new WebMvcConfigurer() {
           @Override
           public void addViewControllers(ViewControllerRegistry registry) {
               registry.addViewController("/").setViewName("login");
               registry.addViewController("/index.html").setViewName("login");
               registry.addViewController(("/main.html")).setViewName("dashboard");
           }

            *//*@Override
            protected void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
            }*//*

            *//*@Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login","/webjars","/asserts");
            }*//*
       };
       return adapter;
   }*/

    /**
     * 对所有请求增加登录拦截
     * 过滤掉 静态资源的访问和登录的请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**");
    }

   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assert/**").addResourceLocations("classpath:/assert/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }*/
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
}
