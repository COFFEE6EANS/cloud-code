package com.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

public class PostZuulFilter extends com.netflix.zuul.ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("这是PostZuulFilter");
        //获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //处理返回中文乱码
//        ctx.getResponse().setCharacterEncoding(StandardCharsets.UTF_8.name());
//        ctx.getResponse().setCharacterEncoding("UTF-8");
        ctx.getResponse().setContentType("text/html;charset=UTF-8");
        String responseBody = ctx.getResponseBody();
        if (null != responseBody){
            //禁止向下路由
            ctx.setResponseStatusCode(500);
            ctx.setResponseBody(responseBody);
        }
        return null;
    }
}
