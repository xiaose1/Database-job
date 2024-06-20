package com.bbs;

import com.bbs.common.CommonConfiguration;
import com.bbs.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * AdminConfiguration
 *
 * @author yangjiajia
 * @createdAt 2024/4/1 17:22
 */
@Configuration
@Import({
        CommonConfiguration.class
})
public class AdminConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/login-code", "/logout")
                .excludePathPatterns("/image/**")
        ;
    }
}
