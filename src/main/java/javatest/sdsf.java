package javatest;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2022/8/3 17:20
 */

public class sdsf {
	private String a;
	private String b;

	static {
		System.out.println("静态代码块");
	}

	public String getAbc() {
		return a;
	}

	public void setAbc(String abc) {
		System.out.println("赋值");
		this.a = abc;
	}

	public sdsf() {
		System.out.println("构造器");
	}

	{
		System.out.println("代码块");
	}

	public static void main(String[] args) {
		sdsf sdsf = new sdsf();
		System.out.println("");
	}
}