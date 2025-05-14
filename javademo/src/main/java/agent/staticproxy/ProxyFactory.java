package agent.staticproxy;


/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/4/19 14:54
 */

public class ProxyFactory implements Factory {
	private CarFactory carFactory;

	public ProxyFactory(CarFactory carFactory) {
		this.carFactory = carFactory;
	}


	@Override
	public void production(String s) {
		carFactory.production(s);
		System.out.println("代理工厂生产" + s + "汽车的装饰品");
	}

	public static void main(String[] args) {
		CarFactory carFactory = new CarFactory();
		ProxyFactory proxyFactory = new ProxyFactory(carFactory);
		proxyFactory.production("001号");
	}
}