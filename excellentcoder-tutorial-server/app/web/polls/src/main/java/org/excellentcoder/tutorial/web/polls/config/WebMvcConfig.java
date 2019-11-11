/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置
 *
 * Spring的Java配置方式是通过 @Configuration 和 @Bean 注解实现的：
 *  a、@Configuration 作用于类上，相当于一个xml配置文件
 *  b、@Bean 作用于方法上，相当于xml配置中的<bean>
 *
 * @author xbyan
 * @version $Id: WebMvcConfig.java, v 0.1 2019-11-08 7:19 PM xbyan Exp $$
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    /**
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addCorsMappings(CorsRegistry)
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
            .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
            .maxAge(MAX_AGE_SECS);
    }
}
