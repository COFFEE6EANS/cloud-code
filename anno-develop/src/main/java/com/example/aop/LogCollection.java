package com.example.aop;

import com.example.data.LoggerEntity;
import com.example.data.LoggerUtils;
import com.example.data.RequestData;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author guojianfeng.
 * @Date Created in  2019/3/1
 * @Description：
 */

@Component
@Aspect
@Slf4j
public class LogCollection {

    private ThreadLocal<LoggerEntity> loggerEntityThreadLocal = new ThreadLocal<>();

    //Service层切点     用于记录错误日志
    @Pointcut("@annotation(com.example.anno.FlagAnno)")
    public void webLog() {

    }

    @Before("webLog()")
    public void logBefor(JoinPoint joinPoint) throws Throwable{
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (!ObjectUtils.isEmpty(attributes)) {
            HttpServletRequest request = attributes.getRequest();
            Class<?> cls = Class.forName(className);
            LoggerEntity logEntity = new LoggerEntity();
            LoggerEntity logClass = LoggerUtils.getAnnClass(cls, logEntity);
            LoggerEntity logMethod = LoggerUtils.getAnnMethod(cls, methodName, logClass);
            logEntity = LoggerUtils.getHeader(request, logMethod);
            RequestData requestData = new RequestData();
            String header = LoggerUtils.getHeaders(request);
            String params = LoggerUtils.getParams(request);
            String args = LoggerUtils.getArgs(joinPoint);
            requestData.setHeader(header);
            requestData.setParam(params);
            requestData.setArgs(args);
            String url = request.getRequestURI();
            String sessionId = LoggerUtils.getSessionId(request);
            logEntity.setStartTime(System.currentTimeMillis());
            logEntity.setClientIp(LoggerUtils.getClientIp(request));
            logEntity.setClassName(className);
            logEntity.setClassMethod(methodName);
            logEntity.setMethod(request.getMethod());
            logEntity.setReqType(LoggerUtils.getRequestType(request));
            logEntity.setUrl(url);
            logEntity.setSessionId(sessionId);
            logEntity.setRequestData(requestData.toString());
            this.loggerEntityThreadLocal.set(logEntity);
        }
    }
}
