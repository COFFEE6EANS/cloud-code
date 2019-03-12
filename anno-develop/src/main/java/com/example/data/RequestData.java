package com.example.data;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求数据实体
 *
 * @author liugang
 * @since 2018/12/4
 */

@Data
public class RequestData implements Serializable{

    private static final long serialVersionUID = -7051015512133263376L;

    private String header;
    private String param;
    private String args;
}
