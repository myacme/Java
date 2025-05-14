package agent.staticproxy;


/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/4/19 14:52
 */

public class CarFactory implements Factory{

	@Override
	public void production(String s) {
		System.out.println("汽车厂生产一辆汽车：" + s);
	}
}