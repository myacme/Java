package jvm;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/6/3 下午4:55
 */
public class JavaVersion {

    public static void main(String[] args) {
        System.out.println("Java版本: " + System.getProperty("java.version"));
        System.out.println("JRE路径: " + System.getProperty("java.home"));
    }
}
