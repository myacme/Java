package agent.dynamic;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/4/19 15:02
 */

public class JDKProxy implements InvocationHandler {

	private Factory factory;

	public void setCarFactory(Factory factory) {
		this.factory = factory;
	}

	public Object getProxy(){
		return Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), factory.getClass().getInterfaces(), this);
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object invoke = method.invoke(factory, args);
		String name = method.getName();
		if ("production".equals(name)){

			System.out.println("代理商买出"+ Arrays.toString(args));
		}
		if ("repair".equals(name)){
			System.out.println("代理商送回"+ Arrays.toString(args));
		}
		return invoke;
	}

	public static void main(String[] args) {
		CarFactory carFactory = new CarFactory();
		JDKProxy jdkProxy = new JDKProxy();
		jdkProxy.setCarFactory(carFactory);
		Factory proxy = (Factory)jdkProxy.getProxy();
		proxy.production("001号");
		proxy.repair("001号");
	}
}