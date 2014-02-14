package com.bluecloud.component.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bluecloud.framework.Entry;

/**
 * 
 *<p>数据缓存专用类</p>
 * @author hujun
 *
 */
public final class DataCacheManager {
	private DataCacheManager() {
	}

	//数据字典缓存map
	private static Map dictMap = new HashMap();

	public static void putDictItem(String key, List items) {
		dictMap.put(key, items);
	}

	public static List getDictItem(String key) {
		return (List) dictMap.get(key);
	}

	public static String getValue(String key, String itemKey) {
		String itemValue = "";
		List list = getDictItem(key);
		for (Object obj : list) {
			Entry entry = (Entry) obj;
			if (itemKey.equals(entry.getKey())) {
				itemValue = entry.getValue();
				break;
			}
		}
		return itemValue;
	}
}
