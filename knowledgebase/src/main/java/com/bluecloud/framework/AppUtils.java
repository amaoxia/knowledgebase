package com.bluecloud.framework;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.WebUtils;

public final class AppUtils {
	private AppUtils() {
	}

	static WebApplicationContext wac = null;

	public static Object getSession(HttpServletRequest req, String key) {
		return WebUtils.getSessionAttribute(req, key);
	}

	public static Object getBean(String beanId) {
		if (wac == null) {
			wac = ContextLoader.getCurrentWebApplicationContext();
		}
		return wac.getBean(beanId);
	}

	/**
	 * 获取国际化资源消息
	 * @param key
	 * @param args
	 * @return
	 */
	public static String getMessage(String key, String[] args) {
		if (wac == null) {
			wac = ContextLoader.getCurrentWebApplicationContext();
		}
		return wac.getMessage(key, args, Locale.getDefault());
	}

}
