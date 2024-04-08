package javatest;


import java.io.Serializable;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/1/5 10:44
 */

interface LambdaTestInterface {

	int add(int a, int b);
}

interface LambdaTestInterface1 {

	int add(int a);
}

class LambdaTest implements Serializable {

	public static int add(LambdaTestInterface lti) {
		return lti.add(1, 2);
	}

	public static void main(String[] args) {
		LambdaTestInterface lambdaTest = (a, b) -> a + b;
		System.out.println(lambdaTest.add(1, 2));
		System.out.println(add(Integer::sum));
		LambdaTestInterface lambdaTest1 = Integer::sum;
		System.out.println(lambdaTest1.add(1, 2));
		LambdaTestInterface1 lambdaTest2 = a -> a + 1;
		System.out.println(lambdaTest2.add(1));
	}
}
