package jvm;


import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/4/16 下午2:25
 */
public class JOLDemo {

    public void test() {
        // jvm信息
        System.out.println(VM.current().details());
        //对齐字节数
        System.out.println(VM.current().objectAlignment());
    }

    /**
     * 默认配置，启动了压缩指针，-xx:+UseCompressedClassPointers
     * 关闭压缩指针，-XX:-UseCompressedClassPointers
     * jol
     * @param args
     */
    public static void main(String[] args) {
        Object o = new Object(); //16 bytes
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        Person person = new Person(); //24 bytes
        System.out.println(ClassLayout.parseClass(Person.class).toPrintable());
    }
}

class Person {
    private String name;
    private boolean age;
    private int sex;


}
