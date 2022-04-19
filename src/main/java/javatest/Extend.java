package javatest; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: java.Extend
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/8/27 23:45
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/8/27

 * @since 1.0.0

 */

public class Extend {
    public Extend(){
        System.out.println("new father");
    }
    {
        System.out.println("I'm father");
    }
    static {
        System.out.println("static father");
    }

}
class Sun extends Extend{
    public Sun(){
        System.out.println("new sun");
    }
    {
        System.out.println("I'm sun");
    }
    static {
        System.out.println("static sun");
    }
    public static void to(){
        System.out.println(" method ");
    }

    public static void main(String[] args) {
        System.out.println("---开始---");
        to();
        new Sun();
        new Extend();
        System.out.println("---结束---");
    }
}