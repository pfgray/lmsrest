/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.paulgray.mockrest.config;

import java.util.List;
import net.paulgray.lmsrest.web.DefaultContextUserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author paul
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    
    @Autowired
    DefaultContextUserArgumentResolver defaultContextUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(defaultContextUserArgumentResolver);
        super.addArgumentResolvers(argumentResolvers); //To change body of generated methods, choose Tools | Templates.
    }
    
}
