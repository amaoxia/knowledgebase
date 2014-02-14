package com.bluecloud.framework;

import java.util.HashMap;
import java.util.Map;

public final class AppConstant {
	private AppConstant() {
	}

	public final static String SESSION_USER = "session_user";

	public final static String DATABASE_TYPE = "MYSQL";

	// web应用名
	public final static String APP_PATH_KEY = "webpath";

	public static final String SYSTEM_MARK = "sysmark";

	public static String SYSTEM_MARK_DEFAULT = "000";

	// 系统配置参数map
	public static Map ConfigMap = new HashMap();

	public static void putConfig(String key, String value) {
		ConfigMap.put(key, value);
	}

	// 获取配置参数值
	public static String getConfig(String key) {
		return (String) ConfigMap.get(key);
	}
}
