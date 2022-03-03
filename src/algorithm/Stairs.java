package algorithm;


/**
 * 〈给定一个n(1<n≤45)代表总共有n阶楼梯，一开始在第0阶，每次可以爬1或者2个台阶，问总共有多少种不同的方法可以爬到楼顶。 〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/3/3 15:04
 */

public class Stairs {

	public static void main(String[] args) {
		int[] f = new int[46];
		f[0]= 1;
		f[1]= 1;
		for (int i = 2; i < 46; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		System.out.println(f[20]);
	}


}