package note;
/**
 * @author ljx
 * @version 1.0.0
 * @create 2025/8/12 上午9:41
 */

import java.time.LocalDate;
import java.time.LocalDateTime;

// 其他常用日期操作
public class DateUtils {

    /**
     * 获取 N 天前的日期
     */

    public static LocalDate getDaysAgo(int days) {
        return LocalDate.now().minusDays(days);
    }

    /**
     * 获取 N 天前的日期时间
     *
     * @param days
     * @return
     */
    public static LocalDateTime getDaysAgoWithTime(int days) {
        return LocalDateTime.now().minusDays(days);
    }

    /**
     * 获取 N 个月前的日期
     *
     * @param months
     * @return
     */
    public static LocalDate getMonthsAgo(int months) {
        return LocalDate.now().minusMonths(months);
    }

    /**
     * 获取 N 年前的日期
     *
     * @param years
     * @return
     */
    public static LocalDate getYearsAgo(int years) {
        return LocalDate.now().minusYears(years);
    }

    /**
     * 获取月初日期
     *
     * @return
     */
    public static LocalDate getFirstDayOfMonth() {
        return LocalDate.now().withDayOfMonth(1);
    }

    /**
     * 获取月末日期
     *
     * @return
     */
    public static LocalDate getLastDayOfMonth() {
        return LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
    }
}

