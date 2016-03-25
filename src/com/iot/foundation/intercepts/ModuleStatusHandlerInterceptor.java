package com.iot.foundation.intercepts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * *****************************************************************
 *
 * 功能说明：拦截器
 *
 */
//@Component
public class ModuleStatusHandlerInterceptor extends HandlerInterceptorAdapter {
	static final Logger log = LoggerFactory.getLogger(ModuleStatusHandlerInterceptor.class);

	

	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {
		return true;
	}

	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		try {
			
				
		} catch (Exception ex) {
			log.debug("Error getting forum status in handlerInterceptor", ex);
			throw ex;
		}
	}

	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}
}