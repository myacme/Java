package javatest; /**
 * FileName: java.Regular
 * <p>
 * Author:   liujixiang
 * <p>
 * Date:     2021/3/11 15:23
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ljx
 * @create 2021/3/11
 * @since 1.0.0
 */

public class Regular {
	public static void a() {
		//		String a="共940条记录 1/94页";
		String a = "共找到 483 个小区";
		String regEx = "共找到 (\\d+) 个小区";
		String regEx1 = "/(\\d+)页";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(a);
		if (m.find()) {
			System.out.println(m.group(1));
		}
	}

	public static void b() {
		String a = ".zip";
		String regEx = "^(?!\\.).*$"; //不以.开头
		String regEx1 = "^\\..*$"; //以.开头
		String regEx2 = "^.*(a).*$"; //包含a
		String regEx3 = "^((?!a).)*$"; //不包含
		boolean p = Pattern.matches(regEx1, a);
		System.out.println(p);
	}

	public static void c() {
		String a = "共找到 [请问] 个小[区.]12211[qq::：][配电站(开关站).断路器.本期建设1kV出线]";
		String regEx = "(\\[[^\\[|^\\]|^{|^}]*\\])";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(a);
		int i = 0;
		while (m.find(i)) {
			System.out.println(m.group(1));
			i = m.end();
		}
	}
	public static void d() {
		String a = "t-|2-50.jpg";
		String regEx = "-(^-|[0-9]*).";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(a);
		int i = 0;
		while (m.find(i)) {
			System.out.println(m.group(1));
			i = m.end();
		}
	}


	/**
	 * mybatis 变量提取
	 */
	public static void e() {
		String a = "SELECT itemvalue FROM d_project_parameter WHERE engineeringid = #{engineeringid}, ${qwer} AND projectid = #{projectid} and itemkey = '图签日期'";
		String regEx = "([#|$]\\{[a-z0-9_]*})";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(a);
		int i = 0;
		while (m.find(i)) {
			System.out.println(m.group(1));
			i = m.end();
		}
	}


	/**
	 *
	 */
	public static void f() {
		String a = "起去q1m";
		String regEx = "(\\d)";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(a);
		int i = 0;
		while (m.find(i)) {
			System.out.println(m.group(1));
			i = m.end();
		}
	}

	public static void main(String[] args) {
		f();
	}
}