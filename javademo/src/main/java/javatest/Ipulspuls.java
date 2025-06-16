package javatest;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/9/5 上午11:03
 */
public class Ipulspuls {


    public void add() {
        int i = 1;
        i++;
        int j = 1;
        ++j;
    }

    public static void main(String[] args) {
        int i = 1;
        int j = i++;
        System.out.println(j);
        System.out.println(i);
        i++;
    }
}
