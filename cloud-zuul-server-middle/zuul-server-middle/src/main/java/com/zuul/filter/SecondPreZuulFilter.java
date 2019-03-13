package com.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletRequest;

public class SecondPreZuulFilter extends com.netflix.zuul.ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
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
        System.out.println("这是第二个定义Zuul Filter");
        //获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = ctx.getRequest();
        String a = request.getParameter("a");
        if (null == a){
            //禁止向下路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("{\"status\":500,\"message\":\"a参数为空\"}");
            ctx.set("isPass",false);
        }
        ctx.set("isPass",true);
        return null;
    }
}
