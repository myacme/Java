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
        String s1 = new String("java");
        System.out.println(s1.intern() == s1);
        System.out.println(s == s1);
        String t1 = new String("wolai");
        System.out.println(t1.intern() == t1);
    }

    public static void test2(String str) {
        str = "hello world";
        System.out.println(str);
    }

    /**
     * new string("ab")
     * 对象1：New String()
     * 对象2: 常量池“ab”
     *
     *
     * new string("a")+ new string("b")呢?
     * 对象1: + 隐式创建的 new stringBuilder()
     * 对象2: new string()
     * 对象3: 常量池中的"a"
     * 对象4: new string()
     * 对象5: 常量池中的"b"
     * 深入剖析:stringBuilder的tostring():
     * 对象6: new string(“ab")
     * 强调-下，tostring()的调用，在字符串常量池中，有生成"ab"
     */
    public static void test3() {
        String s1 = new String("1");
        s1.intern();
        String s2 = "1";
        System.out.println(s1 == s2);
        String s3 = new String("1") + new String("1");
        s3.intern();
        //jdk6:创建了一个新的对象"11”,也就有新的地址。
        //jdk7:此时常量中并没有创建”11”,而是创建一个指间堆空间中new string("11”)的地址
        String s4 = "11";
        System.out.println(s3 == s4);
    }

    public static void test4() {
        String s1 = new String("1") + new String("1");
        String s2 = s1.intern();
        //jdk6:创建了一个新的对象"11”,也就有新的地址。
        //jdk7:此时常量中并没有创建”11”,而是创建一个指间堆空间中new string("11”)的地址
        System.out.println(s1 == "11");
        System.out.println(s2 == "11");
    }
    public static void test5() {
        String s = "11";
        String s1 = new String("1") + new String("1");
        String s2 = s1.intern();
        //jdk6:创建了一个新的对象"11”,也就有新的地址。
        //jdk7:此时常量中并没有创建”11”,而是创建一个指间堆空间中new string("11”)的地址
        System.out.println(s1 == "11");
        System.out.println(s2 == "11");
    }
    public static void test6() {
        String s = new String("11");
        String s1 = s.intern();
        //jdk6:创建了一个新的对象"11”,也就有新的地址。
        //jdk7:此时常量中并没有创建”11”,而是创建一个指间堆空间中new string("11”)的地址
        String s2 = "11";
        System.out.println(s == s2);
    }


    public static void main(String[] args) {
        test6();
    }
}