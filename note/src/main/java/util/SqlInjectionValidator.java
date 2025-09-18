package util;

import java.util.regex.Pattern;

/**
 * SQL注入校验工具类
 *
 * @author ljx
 * @version 1.0.0
 * @create 2025/9/17 13:43
 */
public class SqlInjectionValidator {

    /**
     * 常见的SQL关键字和特殊字符模式（不区分大小写）
     */
    private static final String SQL_INJECTION_PATTERN =
            "(?i).*((\\b(select|insert|update|delete|drop|create|alter|exec|union|join|into|from|where|having|order\\s+by|group\\s+by|script|javascript|vbscript|applet|embed|object|link|meta|iframe|frame|form|input|button|textarea|cookie|session|request|response|eval|system|execute|call|declare|cast|convert|waitfor|delay)\\b)|(--|;|/\\*|\\*/|xp_|sp_|' or '1'='1|\" or \"1\"=\"1|or 1=1|'='|'\\s*or\\s*\\w+\\s*=\\s*\\w+|;\\s*(select|insert|update|delete|drop|create|alter|exec|execute|union))).*";

    /**
     * 预编译的正则表达式模式
     */
    private static final Pattern SQL_INJECTION_REGEX = Pattern.compile(SQL_INJECTION_PATTERN);


    private SqlInjectionValidator() {
        throw new UnsupportedOperationException("工具类禁止实例化");
    }

    /**
     * 校验单个字符串是否存在SQL注入风险
     *
     * @param input 待校验的字符串
     * @return true表示存在SQL注入风险，false表示安全
     */
    public static boolean hasSqlInjection(String input) {
        if (StringUtils.isEmpty(input)) {
            return false;
        }
        // 检查是否匹配SQL注入模式
        return SQL_INJECTION_REGEX.matcher(input).matches();
    }

    /**
     * 校验多个字符串是否存在SQL注入风险
     *
     * @param inputs 待校验的字符串数组
     * @return true表示存在SQL注入风险，false表示安全
     */
    public static boolean hasSqlInjection(String... inputs) {
        if (inputs == null) {
            return false;
        }
        for (String input : inputs) {
            if (hasSqlInjection(input)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 抛出SQL注入异常
     *
     * @param message 异常信息
     */
    public static void throwSqlInjectionException(String message) {
        throw new SqlInjectionException("SQL注入风险检测: " + message);
    }

    /**
     * 校验并抛出异常（如果存在SQL注入风险）
     *
     * @param input     待校验的字符串
     * @param fieldName 字段名（用于错误提示）
     */
    public static void validateAndThrow(String input, String fieldName) {
        if (hasSqlInjection(input)) {
            throwSqlInjectionException(fieldName + "包含非法字符");
        }
    }

    public static class SqlInjectionException extends RuntimeException {
        private static final long serialVersionUID = -5127629336779130221L;

        public SqlInjectionException(String message) {
            super(message);
        }
    }
}
