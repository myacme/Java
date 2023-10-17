package javatest;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2023/8/8 14:53
 */

public class ConstClass {


	public static final String HELLO = "hello";

	static {
		System.out.println("ConstClass init");
	}
}


class NotInitialization {
	public static void main(String[] args) {
		System.out.println(ConstClass.HELLO);
	}
}