package note;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2022/9/23 9:46
 */

public class LoggerTest {

	private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

	public static void main(String[] args) {
		logger.info("日志输出");
	}
}