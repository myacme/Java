package com.example.boot.aspect;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2023/2/15 10:44
 */
@Aspect
@Component
public class MyAspect {
	/**
	 * 定义切点，切点是joinpoint集合，每个joinpoint是一个被切面拦截的方法
	 */
	@Pointcut(value = "execution( * com.example.boot.controller.MyController.*(..))")
	public void helloPointCut() {
	}

	/**
	 * before 目标方法执行前执行，前置通知  ==>token验证
	 * after 目标方法执行后执行，后置通知  =》记录操纵日志
	 * after returning 目标方法返回时执行 ，后置返回通知 =》记录操纵日志
	 * after throwing 目标方法抛出异常时执行 异常通知   ==>记录异常日志
	 * around 在目标函数执行中执行，可控制目标函数是否执行，环绕通知 ==>token验证
	 */

	@Around("helloPointCut()")
	public Object aroundHello(ProceedingJoinPoint pjp) throws Throwable {
		//从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		//获取切入点所在的方法
		Method method = signature.getMethod();
		String className = pjp.getTarget().getClass().toString();
		String methodName = pjp.getSignature().getName();
		Object[] parameters = pjp.getArgs();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("调用前：" + className + "." + methodName + "传递的参数为" + mapper.writeValueAsString(parameters));
		Object obj = pjp.proceed();
		System.out.println("调用后 ：" + className + "." + methodName + "返回值" + mapper.writeValueAsString(obj));
		return obj;
	}

	/**
	 * 前置通知, 在方法执行之前执行
	 */
	@Before("helloPointCut()")
	public void beforeHello() {
		System.out.println("beforeHello");
	}

	/**
	 * 后置通知, 在方法执行之后执行
	 */
	@After("helloPointCut()")
	public void afterHello() {
		System.out.println("afterHello");
	}

	/**
	 * 返回通知, 在方法返回结果之后执行
	 */
	@AfterReturning(pointcut = "helloPointCut()", returning = "result")
	public void afterReturnHello(Object result) {
		System.out.println("afterHelloreturn：" + result.toString());
	}

	/**
	 * 异常通知, 在方法抛出异常之后
	 */
	@AfterThrowing(pointcut = "helloPointCut()", throwing = "ex")
	public void AfterThrowingHello(Exception ex) {
		System.out.println("AfterThrowingHello" + ex.getMessage());
	}
}