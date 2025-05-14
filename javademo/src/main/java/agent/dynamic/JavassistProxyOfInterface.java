package agent.dynamic;


import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/4/20 11:00
 */

public class JavassistProxyOfInterface implements MethodHandler {

	private Factory factory;

	 public JavassistProxyOfInterface(Factory factory){
		 this.factory = factory;
	 }

	@Override
	public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
		Object invoke = method.invoke(factory, objects);
		System.out.println("javassist代理接口");
		return invoke;
	}

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(new Class[]{Factory.class});
		Class<?> aClass = proxyFactory.createClass();
		Factory factory = (Factory) aClass.getDeclaredConstructor().newInstance();
		((ProxyObject)factory).setHandler(new JavassistProxyOfInterface(new CarFactory()));
		factory.production("001号");
	}
}

class JavassistProxyOfClass implements MethodHandler {
	@Override
	public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
		Object invoke = method1.invoke(o, objects);
		System.out.println("javassist代理类");
		return invoke;
	}

	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setSuperclass(CarFactory.class);
		Class<?> aClass = proxyFactory.createClass();
		CarFactory carFactory = (CarFactory) aClass.getDeclaredConstructor().newInstance();
		((ProxyObject)carFactory).setHandler(new JavassistProxyOfClass());
		carFactory.production("001号");
	}
}