package javatest;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author MyAcme
 * @create 2020/1/3
 * @since 1.0.0
 */

class StringTest {
    public static void test() {
        String s = "java";
        String t = "wolai";
        System.out.println(s.intern());
        System.out.println(t.intern());
        System.out.println(s.intern() == s);
        System.out.println(t.intern() == t);
        String s1 = new StringBuffer("java").toString();
        System.out.println(s1.intern() == s1);
        System.out.println(s == s1);
        String t1 = new StringBuffer("wolail").toString();
        System.out.println(t1.intern() == t1);
    }

    public static void test2(String str) {
        str = "hello world";
        System.out.println(str);
    }

    public static void main(String[] args) {
        String str = "hello";
        test2(str);
        System.out.println(str);
    }
}