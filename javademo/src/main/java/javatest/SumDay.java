package javatest; /**
 * FileName: java.SumDay
 * <p>
 * Author:   liujixiang
 * <p>
 * Date:     2021/3/25 16:19
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.util.Calendar;
import java.util.Date;

/**
 * 〈计算今天是一年中的第几天〉<br>
 * 〈〉
 *
 * @author ljx

 * @create 2021/3/25

 * @since 1.0.0

 */

public class SumDay {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
	}


}