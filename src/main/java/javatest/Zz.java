package javatest; /**
 * FileName: java.Zz
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

public class Zz {
	public static void main(String[] args) {
//		String a="共940条记录 1/94页";
		String a="共找到 483 个小区";
		String regEx="共找到 (\\d+) 个小区";
		String regEx1="/(\\d+)页";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(a);
		if (m.find()){
			System.out.println( m.group(1));
		}

	}


}