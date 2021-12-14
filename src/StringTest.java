/**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: string
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/1/3 9:53
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author MyAcme
 * @create 2020/1/3
 * @since 1.0.0
 */

 class StringTest {
    public static void main(String[] args) {
        String s = " ";
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {

            try {
                Scanner reader = new Scanner(System.in);
                s = reader.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (s.equals("#")) {
                System.out.println("输入结束！");
                break;
            }
            stringBuffer.append(s).append(";");
        }
        String str = stringBuffer.substring(0);
        String[] slist = str.split(";");
        Set<String> stringSet = new HashSet<>();
        List<Integer > list = new ArrayList<>();
        for (int i = 0; i < slist.length; i++) {
            String[] slist1 = slist[i].split(" ");
            for (int j = 0; j < slist1.length; j++) {
                stringSet.add(slist1[j]);
            }
            list.add( stringSet.size());
            stringSet.clear();
        }
        for (Integer e : list) {
            System.out.println(e);
        }


    }


}