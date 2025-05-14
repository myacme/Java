package javatest;


/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/6/7 15:41
 */

public class YunSuan {

	public static String getType(Object o) {
		return o.getClass().getSimpleName();
	}

	public static void main(String[] args) {
		int i1 = 1;
		short s1 = 1;
		short s2 = 1;
		System.out.println("short + short :" + getType((s1 + s2)));
		System.out.println("short + short :" + getType(((short)(s1 + s2))));
		System.out.println("short + int :" + getType(s1 + i1));
		System.out.println("1 + 1 :" + getType(1 + 1));
	}
}