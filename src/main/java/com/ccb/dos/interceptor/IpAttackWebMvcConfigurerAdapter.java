package com.ccb.dos.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author A
 * @date 2020/2/21 22:59
 */
@Configuration
public class IpAttackWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

  @Bean
  public HandlerInterceptor getMyInterceptor() {
    return new IpAttackInterceptor();
  }

  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/").addResourceLocations("classpath:/static/");
    registry.addResourceHandler("/templates/").addResourceLocations("classpath:/templates/");
    super.addResourceHandlers(registry);
  }

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/toerror");
    super.addInterceptors(registry);
  }
}
