package com.example.boot.controller;


import com.example.boot.annotation.BusLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/7/6 9:24
 */
@RestController
@BusLog("test")
public class MyController {

    @GetMapping("/hello")
    @BusLog("hello")
    public String helloAop(String name) {
        try {
            return "hello! " + name;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}