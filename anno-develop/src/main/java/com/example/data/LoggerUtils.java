package com.example.data;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 日志记录工具类
 *
 * @author liugang
 * @since 2018/12/4
 */
@Slf4j
public class LoggerUtils {

    private static final String UNKNOWN = "unknown";

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || Objects.equals(ip.trim(), "") || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || Objects.equals(ip.trim(), "") || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || Objects.equals(ip.trim(), "") || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        String[] arr = ip.split(",");
        int var4 = arr.length;
        for (String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }

        return ip;
    }

    public static String getRequestType(HttpServletRequest request) {
        String type = request.getHeader("X-Requested-With");
        return StringUtils.isEmpty(type) ? "普通请求" : "X-Requested-With:" + type;
    }

    public static LoggerEntity getAnnClass(Class<?> className, LoggerEntity entity) {
        if (StringUtils.isEmpty(className)) {
            return entity;
        } else {
            Annotation[] annotations = className.getAnnotations();
            if (annotations.length == 0) {
                return entity;
            } else {
                Api api = (Api) className.getAnnotation(Api.class);
                if (StringUtils.isEmpty(api)) {
                    return entity;
                } else {
                    String description = api.description();
                    entity.setSwaggerApi(description);
                    return entity;
                }
            }
        }
    }

    public static LoggerEntity getAnnMethod(Class<?> className, String methodName, LoggerEntity entity) {
        if (StringUtils.isEmpty(className)) {
            return entity;
        } else if (StringUtils.isEmpty(methodName)) {
            return entity;
        } else {
            Method[] methods = className.getMethods();
            int length = methods.length;
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    ApiOperation apiOperation = (ApiOperation) method.getAnnotation(ApiOperation.class);
                    if (StringUtils.isEmpty(apiOperation)) {
                        return entity;
                    }

                    entity.setSwaggerApiOperation(apiOperation.value());
                }
            }

            return entity;
        }
    }

    public static String createLog(LoggerEntity entity) {
        return JSONObject.toJSONString(entity);
        /*return (entity.getClientIp() == null ? "127.0.0.1" : entity.getClientIp()) + "|" +
                (entity.getClassName() == null ? "HelloController" : entity.getClassName()) + "|" +
                (entity.getClassMethod() == null ? "Hello" : entity.getClassMethod()) + "|" +
                (entity.getMethod() == null ? "GET" : entity.getMethod()) + "|" +
                (entity.getUrl() == null ? "/hello" : entity.getUrl()) + "|" +
                (entity.getReqType() == null ? "普通请求" : entity.getReqType()) + "|" +
                (entity.getSwaggerApi() == null ? "第一个请求" : entity.getSwaggerApi()) + "|" +
                (entity.getSwaggerApiOperation() == null ? "第一个请求方法" : entity.getSwaggerApiOperation()) + "|" +
                timeFormat(entity.getStartTime()) + "|" +
                (entity.getRequestData() == null ? "json" : entity.getRequestData()) + "|" +
                timeFormat(entity.getReturnTime()) + "|" +
                (entity.getResponseData() == null ? "json" : entity.getResponseData()) + "|" +
                timeFormat(entity.getEndTime()) + "|" +
                (StringUtils.isEmpty(entity.getHttpStatusCode()) ? 0 : entity.getHttpStatusCode()) + "|" +
                (StringUtils.isEmpty(entity.getTimeConsuming()) ? 0L : entity.getTimeConsuming()) + "|" +
                (entity.getUserId() == null ? "admin" : entity.getUserId()) + "|" +
                (entity.getOrgId() == null ? "admin" : entity.getOrgId()) + "|" +
                (entity.getFSysId() == null ? "admin" : entity.getFSysId()) + "|" +
                (entity.getUserName() == null ? "管理员" : entity.getUserName()) + "|" +
                (entity.getUserHost() == null ? "127.0.0.1" : entity.getUserHost()) + "|" +
                (entity.getSessionId() == null ? "sessionId" : entity.getSessionId()) + "|" +
                (entity.getServiceName() == null ? "serviceName" : entity.getServiceName()) + "|";*/
    }

    public static LoggerEntity getHeader(HttpServletRequest request, LoggerEntity logger) throws UnsupportedEncodingException {
        logger.setUserId(StringUtils.isEmpty(request.getHeader("userId")) ? "userId" : request.getHeader("userId"));
        logger.setOrgId(StringUtils.isEmpty(request.getHeader("orgId")) ? "orgId" : request.getHeader("orgId"));
        logger.setFSysId(StringUtils.isEmpty(request.getHeader("fsysId")) ? "fsysId" : request.getHeader("fsysId"));
        logger.setUserName(StringUtils.isEmpty(request.getHeader("userName")) ? "userName" : URLDecoder.decode(request.getHeader("userName"), "utf-8"));
        logger.setUserHost(StringUtils.isEmpty(request.getHeader("userHost")) ? "userHost" : request.getHeader("userHost"));
        return logger;
    }

    public static String getHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map.toString();
    }

    public static String getSessionId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (ObjectUtils.isEmpty(session)) {
            return "sessionId";
        }
        return session.getId();
    }

    public static String getParams(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = (String) parameterNames.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }

        return map.toString();
    }

    public static String getArgs(JoinPoint joinPoint) {
        return Arrays.toString(joinPoint.getArgs());
    }

    private static String timeFormat(Long l) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return l != null && l > 0L ? sdf.format(l) : "0";
    }
}