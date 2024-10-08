package com.example.boot.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/4/8 11:25
 */
public class MybatisUtil {

	public static List<String> extractVariable(String sql) {
		List<String> list = new ArrayList<>();
		String regEx = "([#|$]\\{[A-Za-z0-9_.]*})";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(sql);
		int i = 0;
		while (matcher.find(i)) {
			list.add(matcher.group(1));
			i = matcher.end();
		}
		return list;
	}

	public static String replaceVariable(String sql, Map<String, Object> params, List<String> variables) {
		for (String variable : variables) {
			if (variable.contains("#")) {
				sql = sql.replace(variable, precompile(params.get(variable.replaceAll("[#{}]", "")).toString()));
			}
			if (variable.contains("$")) {
				sql = sql.replace(variable, params.get(variable.replaceAll("[${}]", "")).toString());
			}
		}
		return sql;
	}


	public static String replaceVariable(String sql, Map<String, Object> params) {
		List<String> variables = extractVariable(sql);
        return replaceVariable(sql, params, variables);
	}

	public static String precompile(String sql) {
		return "'" + sql + "'";
	}

	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<>(4);
		params.put("engineeringid", 1);
		params.put("projectid", 1);
		params.put("qwer", 1);
		String sql = "卷册检索号\n" +
                "${A1.1}#{1_1}\n";
		List<String> list = extractVariable(sql);
//		String replaced = replaceVariable(sql, params, list);
//		System.out.println(replaced);
        System.out.println();
	}
}