package com.example.boot;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 〈〉
 *
 * @author ljx
 * @version 1.0.0
 * @create 2021/11/26 10:31
 */
@Component
public class MyFilter implements Filter {

	private static final ObjectMapper JACKSON = new ObjectMapper();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器启用！！！！！！！！！！！！！！！！！");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		MyHttpRequest requestWrapper = new MyHttpRequest(request);
		String body = requestWrapper.getBody();
		if (StringUtils.isNotEmpty(body)) {
			// 对参数进行处理
			Map<String, Object> params = JACKSON.readValue(body, Map.class);
			Map<String, Object> map = Base64Converter.decode(params, Map.class);
			// 由于body是设置成不可变的，所以需要重新创建一个request，将body设置进去
			requestWrapper = new MyHttpRequest(requestWrapper, JACKSON.writeValueAsBytes(map));
		}
		filterChain.doFilter(requestWrapper, servletResponse);
	}

	@Override
	public void destroy() {
		System.out.println("过滤器注销！！！！！！！！！！！！！！！！！");
	}
}