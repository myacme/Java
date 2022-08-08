package javatest.inf;


/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/7/4 15:04
 */

public class TestImpl extends TestSuper implements TestInterface {
	private String name;

	@Override
	public void interf() {
		System.out.println("interf()");
	}

	public void impl() {
		System.out.println("impl()");
	}

	public static void main(String[] args) {
		TestInterface ti = new TestImpl();
		TestSuper ts = new TestImpl();
		System.out.println();
	}
}