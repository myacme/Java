package javatest;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/5 上午11:03
 */
public class Ipulspuls {


    public static void add() {
        int i = 1;
        i++;
        int j = 1;
        ++j;
        System.out.println(j);
        System.out.println(i);
    }

    public static void add1() {
        int i = 1;
        i = i++;
        int j = 1;
        j = ++j;
        System.out.println(j);
        System.out.println(i);
    }

    public static void main(String[] args) {
        add();
        add1();
    }
}
