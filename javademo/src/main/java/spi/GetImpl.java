package spi;


import spi.service.HelloService;

import java.util.ServiceLoader;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/5/12 下午4:07
 */
public class GetImpl {

    public static void main(String[] args) {


        ServiceLoader<HelloService> serviceLoader = ServiceLoader.load(HelloService.class);
        for (HelloService service : serviceLoader) {
            service.sayHello();
        }
    }
}
