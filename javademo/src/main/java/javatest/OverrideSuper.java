package javatest;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2023/7/11 17:50
 */

class SuperClass {
	public String name = "爹";
}

/**
 * @author liujixiang
 */
public class OverrideSuper extends SuperClass {

	public String name = "儿";

	public static void main(String[] args) {
		SuperClass superClass = new SuperClass();
		SuperClass overrideSuper = new OverrideSuper();
		System.out.println(superClass.name);
		System.out.println(overrideSuper.name);
	}
}

