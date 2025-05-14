package agent.dynamic;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/4/19 15:30
 */

public class CGLIBProxy implements MethodInterceptor {

	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("代理前");
		Object invokeSuper = methodProxy.invokeSuper(o, objects);
		System.out.println("代理销售汽车"+ Arrays.toString(objects));
		return invokeSuper;
	}

	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CarFactory.class);
		enhancer.setCallback(new CGLIBProxy());
		CarFactory carFactory = (CarFactory) enhancer.create();
		carFactory.production("001号");
	}
}