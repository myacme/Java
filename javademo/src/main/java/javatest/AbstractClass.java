package javatest;


/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/6/29 11:22
 */

public abstract class AbstractClass {

	public abstract void abc(int a);

	
	public static AbstractClass getInstance(){
		AbstractClass abstractClass = new AbstractClass() {
			@Override
			public void abc(int a) {
			}
		};
		return abstractClass;
	}



}