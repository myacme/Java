package javatest;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/6/11 下午3:46
 */
public class 累称 {
	public int func(int n ){
		return n = n>1 ? n*func(n-1):1;
	}

	public static void main(String[] args) {
		累称 fun = new 累称();
		System.out.println(fun.func(9));
	}



}
