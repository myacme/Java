package com.example.boot.Interceptor;


import io.jsonwebtoken.ExpiredJwtException;
import lab.captain.pwy.bean.Result;
import lab.captain.pwy.bean.ResultStatus;
import lab.captain.pwy.util.IpUtil;
import lab.captain.pwy.util.JacksonUtil;
import lab.captain.pwy.util.JwtTokenUtil;
import lab.captain.pwy.util.LogonUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * JWT拦截器
 *
 * @author ljx
 * @version 1.0.0
 * @create 2022/7/21 10:41
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader("token");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		if (token == null) {
			response.getWriter().println(JacksonUtil.getJsonString(Result.error(ResultStatus.NO_AUTH)));
			log.info("token不能为空");
			return false;
		}
		try {
			if (!JwtTokenUtil.validateToken(token)) {
				response.getWriter().println(JacksonUtil.getJsonString(Result.error(ResultStatus.NO_AUTH)));
				log.info("token解析错误");
				return false;
			}
			if (LogonUserUtil.verifyLogonUser(IpUtil.getIpAddr(request), JwtTokenUtil.getSubject(token))) {
				response.getWriter().println(JacksonUtil.getJsonString(Result.error("401", "未登录，请先登录", new Date(), null)));
				log.info("未登录，请先登录");
				return false;
			}
		} catch (ExpiredJwtException ex) {
			response.getWriter().println(JacksonUtil.getJsonString(Result.error("401", "登录信息已过期，请从新登录", new Date(), null)));
			log.info("token过期需要重新登录");
			return false;
		} catch (Exception e) {
			response.getWriter().println(JacksonUtil.getJsonString(Result.error(ResultStatus.NO_AUTH)));
			log.info("token解析错误:{}", e.getMessage());
			return false;
		}
		return true;
	}
}
