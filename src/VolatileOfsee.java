/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2021/12/13 10:14
 */

public class VolatileOfsee {

	volatile int number = 0;

	void update() {
		this.number = 10;
	}

	public static void main(String[] args) {
		VolatileOfsee volatileOfsee = new VolatileOfsee();
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " number value: " + volatileOfsee.number);
			volatileOfsee.update();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " number value: " + volatileOfsee.number);
		}, "aa").start();
		int result = volatileOfsee.number + 1;
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result += volatileOfsee.number;
		System.out.println(result);
	}
}