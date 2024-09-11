package note;


import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2022/9/30 11:37
 */

public class DateTest {


    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDate.now());
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(new Date());
        DateTest dateTest = new DateTest();
        dateTest.comparisonTime();
        System.out.println(new Date().getTime());
        dateTest.timeDifference();
    }

    @Test
    public void yaerDay() {
        int year = Year.now().getValue();
        LocalDate yearStart = LocalDate.of(year, 1, 1);
        LocalDate yearEnd = LocalDate.of(year, 12, 31);
        long daysInYear = ChronoUnit.DAYS.between(yearStart, yearEnd) + 1;
        System.out.println("Days in year " + year + ": " + daysInYear);
    }

    /**
     * 获取今天是今年的第几天
     */
    @Test
    public void dayOfYear() {
        LocalDate today = LocalDate.now();
        int dayOfYear = today.getDayOfYear();
        System.out.println("今天是今年的第 " + dayOfYear + " 天。");
    }

    /**
     * 获取今天是本月的第几天
     */
    @Test
    public void dayOfMonth() {
        LocalDate today = LocalDate.now();
        int dayOfMonth = today.getDayOfMonth();
        System.out.println("今天是本月的第 " + dayOfMonth + " 天。");
    }

    //获取今天的日期
    public void getCurrentDate() {
        LocalDate today = LocalDate.now();
        System.out.println("Today's Local date : " + today);
        //这个是作为对比
        Date date = new Date();
        System.out.println(date);
    }

    //获取年、月、日信息
    public void getDetailDate() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("Year : %d  Month : %d  day : %d t %n", year, month, day);
    }

    //处理特定日期
    public void handleSpecilDate() {
        LocalDate dateOfBirth = LocalDate.of(2018, 01, 21);
        System.out.println("The specil date is : " + dateOfBirth);
    }

    //判断两个日期是否相等
    public void compareDate() {
        LocalDate today = LocalDate.now();
        LocalDate date1 = LocalDate.of(2018, 01, 21);
        if (
                date1.equals(today)) {
            System.out.printf("TODAY %s and DATE1 %s are same date %n", today, date1);
        }
    }

    //处理周期性的日期
    public void cycleDate() {
        LocalDate today = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(2018, 01, 21);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);
        if (
                currentMonthDay.equals(birthday)) {
            System.out.println("Many Many happy returns of the day !!");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }
    }

    //获取当前时间
    public void getCurrentTime() {
        LocalTime time = LocalTime.now();
        System.out.println("local time now : " + time);
    }

    //增加小时
    public void plusHours() {
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(2);
        // 增加两小时
        System.out.println("Time after 2 hours : " + newTime);
    }

    //如何计算一周后的日期
    public void nextWeek() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 week : " + nextWeek);
    }

    //计算一年前或一年后的日期
    public void minusDate() {
        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year : " + previousYear);
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Date after 1 year : " + nextYear);
    }

    public void clock() {
        // 根据系统时间返回当前时间并设置为UTC。
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);
        // 根据系统时钟区域返回时间
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + clock);
    }

    //如何用Java判断日期是早于还是晚于另一个日期
    public void isBeforeOrIsAfter() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2018, 1, 29);
        if (tomorrow.isAfter(today)) {
            System.out.println("Tomorrow comes after today");
        }
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(today)) {
            System.out.println("Yesterday is day before today");
        }
    }

    //时区处理
    public void getZoneTime() {
        //设置时区
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("现在的日期和时间在特定的时区 : " + dateAndTimeInNewYork);
    }

    //使用 YearMonth类处理特定的日期
    public void checkCardExpiry() {
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2028, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }

    //检查闰年
    public void isLeapYear() {
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println("2018 is not a Leap year");
        }
    }

    //计算两个日期之间的天数和月数
    public void calcDateDays() {
        LocalDate today = LocalDate.now();
        LocalDate java8Release = LocalDate.of(2018, Month.MAY, 14);
        Period periodToNextJavaRelease = Period.between(today, java8Release);
        System.out.println(periodToNextJavaRelease.getMonths());
    }

    // 包含时差信息的日期和时间
    public void ZoneOffset() {
        LocalDateTime datetime = LocalDateTime.of(2018, Month.FEBRUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println(date);
    }

    // 获取时间戳
    public void getTimestamp() {
        Instant timestamp = Instant.now();
        System.out.println(timestamp.toEpochMilli());
    }

    // 使用预定义的格式化工具去解析或格式化日期
    public void formateDate() {
        String dayAfterTommorrow = "20180210";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);
    }

    /**
     * 判断时间是否超过三个月
     */
    public void comparisonTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dayAfterTommorrow = "2024-01-03 11:11:11";
        LocalDateTime formatted = LocalDateTime.parse(dayAfterTommorrow, formatter);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.plusMonths(3);
        System.out.print(localDateTime.isBefore(formatted));
        System.out.println(LocalDateTime.now().plusMonths(3).isBefore(formatted));
    }

    public void timeDifference() {
        LocalTime time1 = LocalTime.of(10, 30);
        LocalTime time2 = LocalTime.of(15, 45);
        Duration duration = Duration.between(time1, time2);
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long seconds = duration.getSeconds();
        System.out.println("时间差（小时）：" + hours);
        System.out.println("时间差（分钟）：" + minutes);
        System.out.println("时间差（秒）：" + seconds);
    }

    /**
     * LocalDate转String
     *
     * @param localDate localDate对象
     * @param pattern   格式示例： yyyy-MM-dd
     * @return
     */
    public static String LocalDatetoStr(LocalDate localDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(formatter);
    }

    /**
     * String转LocalDate
     *
     * @param dateStr 日期的字符串
     * @param pattern 格式示例： yyyy-MM-dd
     * @return
     */
    public static LocalDate toLocalDate(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * Date转LocalDateTime
     *
     * @param date   jdk6 日期对象
     * @param zoneId 时区id
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
        // toInstant()：将Date对象转换成为Instant(瞬时)对象
        // ofInstant(): 将瞬时对象转换成为LocalDateTime对象
        Instant instant = date.toInstant();
        if (zoneId == null) {
            zoneId = ZoneId.systemDefault();
        }
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * LocalDateTime转Date
     *
     * @param localDateTime 日期时间对象
     * @return
     */
    public static Date LocalDateTimetoDate(LocalDateTime localDateTime, ZoneId zoneId) {
        if (zoneId == null) {
            zoneId = ZoneId.systemDefault();
        }
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     *
     * @param localDateTime
     * @return
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }
}