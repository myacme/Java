package javatest; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: java.ExceptionTest
 * <p>
 * Author:   liujixiang
 * <p>
 * Date:     2020/12/3 15:23
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ljx
 * @create 2020/12/3
 * @since 1.0.0
 */

public class ExceptionTest {

	String a;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public static String aad() {
		String a = "try";
		try {
			int i = 1;

			return a;
		} catch (Exception e) {
			return a;

		} finally {
			 a = "finally";
		}
	}

	public static ExceptionTest aab() {
		ExceptionTest exceptionTest = new ExceptionTest();
		try {
			String a = "try";
			exceptionTest.setA(a);
			return exceptionTest;
		} catch (Exception e) {
			e.printStackTrace();
			String a = "catch";
			exceptionTest.setA(a);
			return exceptionTest;

		} finally {
			String a = "finally";
			exceptionTest.setA(a);
		}
	}
	public static int aai() {
		int i =10;
		try {
			return i;
		} catch (Exception e) {
			e.printStackTrace();

			return i;

		} finally {
			i=100;
		}
	}

	public static void main(String[] args) {
		System.out.println(aad());
		System.out.println(aab().getA());
		System.out.println(aai());

	}


}