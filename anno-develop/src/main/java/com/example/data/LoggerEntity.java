package com.example.data;

import lombok.Data;

import java.io.Serializable;

/**
 * 日志实体记录类
 *
 * @author liugang
 * @since 2018/12/4
 */
@Data
public class LoggerEntity implements Serializable{

    private static final long serialVersionUID = -2176857271625269122L;

    /**
     * 客户端IP
     * */
    private String clientIp;
    /**
     * 类名
     * */
    private String className;
    /**
     * 代码方法
     * */
    private String classMethod;
    /**
     * 请求方法
     * */
    private String method;
    /**
     * 请求uri
     * */
    private String url;
    /**
     * 请求类型
     * */
    private String reqType;
    /**
     * swagger的API
     * */
    private String swaggerApi;
    /**
     * swagger的API注解
     * */
    private String swaggerApiOperation;
    /**
     * session Id
     * */
    private String sessionId;
    /**
     * 开始时间
     * */
    private long startTime;
    /**
     * 请求数据
     * */
    private String requestData;
    /**
     * 接口返回时间
     * */
    private long returnTime;
    /**
     * 返回数据
     * */
    private String responseData;
    /**
     * 结束时间
     * */
    private long endTime;
    /**
     * http 状态码
     * */
    private Integer httpStatusCode;
    /**
     * 消耗时间
     * */
    private long timeConsuming;
    /**
     * 用户 Id
     * */
    private String userId;
    /**
     * 机构 Id
     * */
    private String orgId;
    /**
     * 系统 Id
     * */
    private String fSysId;
    /**
     * 用户名
     * */
    private String userName;
    /**
     * 用户Host
     * */
    private String userHost;
    /**
     * 服务名
     * */
    private String serviceName;
}
