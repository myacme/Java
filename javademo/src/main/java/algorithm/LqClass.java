package algorithm;


/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/3/8 17:19
 */

public class LqClass {

	/**
	 * 最大公约数
	 * gcd函数（欧几里得算法原理）
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	/**
	 * 最小公倍数
	 * lcm函数（速求最小公倍数，原理基于gcd函数）最小公倍数=两数之积÷两数最大公约数
	 * @param a
	 * @param b
	 * @return
	 */
	public static int lmc(int a,int b){
		return a * b / gcd(a, b);
	}


	public static void main(String[] args) {
		int a= 90;
		int b = 45;
		String s = "1";
		System.out.println(gcd(a,b));
	}

}