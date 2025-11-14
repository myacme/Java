package com.example.boot;

import com.example.boot.util.AspectSpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author MyAcme
 */
@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class BootApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootApplication.class, args);
        AspectSpringContextUtil.getBean("loginUser");
        Environment environment = context.getBean(Environment.class);
        log.info("指标服务访问路径为：http://127.0.0.1:{}{}/swagger-ui.html",
                environment.getProperty("server.port"),
                environment.getProperty("server.servlet.context-path"));

	}

}