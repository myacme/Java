package javatest;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/3/31 下午2:23
 */
public class Stringpuls {
    public static void main1() {
        String input = "1";
        String input2 = "2";
        String input3 = input + input2;
    }

    public static void main2() {
        StringBuilder input3 = new StringBuilder("1").append("2");
    }

    public static void main(String[] args) {
        String input = "1" + "2";
    }
}
