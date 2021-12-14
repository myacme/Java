/**
 * FileName: MidSreach
 * <p>
 * Author:   liujixiang
 * <p>
 * Date:     2021/3/26 15:47
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
 * @author ljx
 * @create 2021/3/26
 * @since 1.0.0
 */

public class MidSreach {
	private static int count=0;
	public static void Mid(int[] arr, int i, int low, int high) {
		count++;
		int mid = (low + high) / 2;
		if (low==mid) {
			System.out.println(arr[low] == i);
		} else {
			if (i < arr[mid]) {
				Mid(arr, i, low, mid);
			} else if (i > arr[mid]) {
				Mid(arr, i, mid, high);
			} else {
				System.out.println(mid);
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = {1, 3, 6, 7, 8, 9, 10, 14, 16, 20, 26, 37, 50, 66, 90};
		Mid(arr, 3, 0, arr.length);
		System.out.println(count);
	}
}