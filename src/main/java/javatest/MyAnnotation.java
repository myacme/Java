package javatest; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: java.MyAnnotation
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

public class MyAnnotation {

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
        MyAnnotation.a++;
        MyAnnotation nktest =  new MyAnnotation();
        System.out.println("a= " + a + "t.a= " + nktest.a);
    }


}

 @interface myAnnotation{
    int value() ;
}

// 反射
class Invoke{
    public static void main(String[] args) {
        Class c = MyAnnotation.class;
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