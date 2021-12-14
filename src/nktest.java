/**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: nktest
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/7/17 12:00
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/7/17

 * @since 1.0.0

 */

public class nktest {

    public static int a = 1;

    @myAnnotation(value = 10)
    public int b =1;

    private double d = 1.01;

    public String toStr(String s){
        System.out.println(s);
        return s;
    }

    public static void main(String[] args) {
        int a=10;
        a++;
        nktest.a++;
        nktest nktest =  new nktest();
        System.out.println("a= " + a + "t.a= " + nktest.a);
    }


}

 @interface myAnnotation{
    int value() ;
}


class fun{
    public int func(int n ){
        return n = n>1 ? n*func(n-1):1;
    }

    public static void main(String[] args) {
        fun fun = new fun();
        System.out.println(fun.func(5));
    }
}
class Inte{
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
class Excp{
    public static void main(String[] args) {
        try {

        }catch (NullPointerException e){

        }catch (Exception e){

        } finally{

        }
    }
}

// 反射
class Invoke{
    public static void main(String[] args) {
        Class c = nktest.class;
        try {
            Method toStr  = c.getMethod("toStr", String.class);
            Field field_a = c.getField("a");
            Field field_b = c.getField("b");
            Field field_d = c.getDeclaredField("d");
            Field[] fields = c.getFields();
            myAnnotation an = field_b.getDeclaredAnnotation(myAnnotation.class);
            Object o = toStr.invoke(c.newInstance(), "abc");
            System.out.println(field_a);
            System.out.println(field_d.getName());
            System.out.println(fields.length);
            System.out.println(an);
            System.out.println(o);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}