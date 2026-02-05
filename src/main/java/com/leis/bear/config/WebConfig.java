package com.leis.bear.config;

import com.leis.bear.interceptor.LoginInterceptor;
import com.leis.bear.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ConditionalOnMissingBean(UserDetails.class)
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       /* registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/doc.html", "/v3/api-docs/**");
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/doc.html", "/v3/api-docs/**");*/
    }

}
