package spi.service.impl;


import spi.service.HelloService;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/5/12 下午4:08
 */
public class HelloServiceImpl1 implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("hello HelloServiceImpl1");
    }
}
