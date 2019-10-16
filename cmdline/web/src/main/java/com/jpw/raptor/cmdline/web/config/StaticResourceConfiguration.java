package com.jpw.raptor.cmdline.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by john on 5/22/18.
 */
@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    //
    // Specify additional resource directory of for document html files
    //
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/html/**")
                .addResourceLocations("file:/home/finance/knowledge/html/");
    }
}
