package javatest;

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
	static class Intern{
		public static void main(String[] args) {
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
	}


}