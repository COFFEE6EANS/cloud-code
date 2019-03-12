package com.example.web;

import com.example.anno.FlagAnno;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guojianfeng.
 * @Date Created in  2019/3/1
 * @Description：
 */
@RestController
public class LogTestRest {

    @FlagAnno
    @RequestMapping("/")
    public String log() {
        return "success";
    }
}
