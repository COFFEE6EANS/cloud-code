package com.zuul.filter.conf;

import com.zuul.filter.FirstPreZuulFilter;
import com.zuul.filter.PostZuulFilter;
import com.zuul.filter.SecondPreZuulFilter;
import com.zuul.filter.ThirdPreZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ZuulConfig {
    @Bean
    public FirstPreZuulFilter firstPreZuulFilter(){
        return new FirstPreZuulFilter();
    }
    @Bean
    public SecondPreZuulFilter secondPreZuulFilter(){
        return new SecondPreZuulFilter();
    }
    @Bean
    public ThirdPreZuulFilter tirdPreZuulFilter(){
        return new ThirdPreZuulFilter();
    }
    @Bean
    public PostZuulFilter postZuulFilter(){
        return new PostZuulFilter();
    }

}
