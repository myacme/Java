package com.example.boot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动加载所有的稽核定时任务
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/1/11 11:20
 */
@Component
public class TimedTaskStart implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(TimedTaskStart.class);


	@Override
	public void run(String... args) {
		logger.info("加载所有的稽核定时任务");
	}
}