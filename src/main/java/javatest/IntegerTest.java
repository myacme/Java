package javatest;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/6/11 下午3:44
 */
public class IntegerTest {

	public static void main(String[] args) {
		Integer i1 = 56;
		String s1="56";
		int i2 = 56;
		String string = i2 + "00";
		Integer i3 = Integer.valueOf(s1);
		Integer i4 = new Integer(56);
		Integer i5 = new Integer(56);
		Integer i6 = 130;
		Integer i7 = 130;
		Integer i8 = 120;
		Integer i9 = 120;
		Integer i10 = new Integer(130);
		Integer i11 = new Integer(130);
		int i12 = 130;
		Integer s3 = Integer.valueOf("130");

		System.out.println(i1==i2); //t
		System.out.println(i3==i2); //t
		System.out.println(i1==i3);  //t
		System.out.println(i1.equals(i3));//t
		System.out.println(i3==i4);//f
		System.out.println(i5==i4);//f
		System.out.println("6-7:" + (i6==i7));//f
		System.out.println(i8==i9);//t
		System.out.println(i10==i11);//f
		System.out.println(i12==i6);//t
		System.out.println(i12==i11);//t
		System.out.println(i6==s3);//f
		System.out.println(string);
	}


}
