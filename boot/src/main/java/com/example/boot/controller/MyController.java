package com.example.boot.controller;


import com.example.boot.annotation.Log;
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
@Log("test")
public class MyController {

    @GetMapping("/hello")
    @Log("hello")
    public Object helloAop(String name) {
        try {
            String json = null;
            json.length();
            return "hello! " + name;
        } catch (Exception e) {
            e.printStackTrace();
            return e;
        }
    }
}