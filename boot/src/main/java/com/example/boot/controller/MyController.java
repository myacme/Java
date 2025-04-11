package com.example.boot.controller;


import com.example.boot.annotation.Log;
import com.example.boot.sevice.MySevice;
import com.example.boot.sevice.TransactionSevice;
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
    private MySevice mySevice;

    @Resource(name = "multiThreadTransaction")
    private TransactionSevice transactionSevice;

    @GetMapping("/hello")
//    @Log("hello")
    public String helloAop(String name) {
        try {
            mySevice.helloAop(name);
            return "hello! " + name;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/test")
    public String testTransaction() {
        try {
            transactionSevice.testTransaction();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}