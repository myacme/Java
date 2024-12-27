package com.example.boot.controller;


import com.example.boot.annotation.Log;
import com.example.boot.sevice.MySevice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/7/6 9:24
 */
@RestController
@Log("test")
public class MyController {

    @Resource
    private MySevice sevice;

    @GetMapping("/hello")
//    @Log("hello")
    public Object helloAop(String name) {
        try {
            sevice.helloAop(name);
            return "hello! " + name;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }
}