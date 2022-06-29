package javatest;


import java.util.ArrayList;
import java.util.List;

/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/4/24 9:35
 */

public class Param {

	private static void test(List<Integer> list){
		list = new ArrayList<>();
		list.add(2);
		System.out.println(list);
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		System.out.println(list);
		test(list);
		System.out.println(list);
	}


}