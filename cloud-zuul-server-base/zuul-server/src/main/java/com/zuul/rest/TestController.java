package com.zuul.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guojianfeng.
 * @Date Created in  2019/2/1
 * @Description：
 */
@RestController
public class TestController {
    @GetMapping("/client")
    public String client(){
        System.out.println("线程"+Thread.currentThread().getName()+"  forward ...");
        return "forward";
    }
}
