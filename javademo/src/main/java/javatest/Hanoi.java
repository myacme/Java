package javatest; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: java.Hanoi
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/8/12 12:33
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

 * @create 2020/8/12

 * @since 1.0.0

 */

public class Hanoi {

    public static void hanoi(int n, String from, String temp, String to){
        if (n==1){
            System.out.println("第 1 个盘子从"+from+" 移动到 "+to);
        }else {
            hanoi(n-1, from, to, temp);
            System.out.println("第 "+ n +" 个盘子从"+from+" 移动到 "+to);
            hanoi(n-1, temp, from, to);
        }
    }
    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        hanoi(5, "A", "B", "C");
        long endTime=System.currentTimeMillis();
        float excTime=(float)(endTime-startTime);
        System.out.println("执行时间：" + excTime + "ms");
    }
}