package com.client.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guojianfeng.
 * @Date Created in  2019/2/1
 * @Description：
 */
@RestController
public class TestController {

    @GetMapping("/add")
    public Integer add(Integer a,Integer b){
        return a+b;
    }
}
