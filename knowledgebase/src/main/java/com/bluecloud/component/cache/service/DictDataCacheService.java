package com.bluecloud.component.cache.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bluecloud.component.cache.DataCacheManager;
import com.bluecloud.component.sys.entity.po.SysDict;
import com.bluecloud.framework.Entry;
import com.bluecloud.framework.core.mvc.base.BaseService;

@Service
public class DictDataCacheService extends BaseService {
	/**
	 * 将所有数据字典中定义的数据加载至缓存中
	 * 
	 * @throws Exception
	 */
	public void loadDictData() throws Exception {
		try {
			List<SysDict> dictList = new ArrayList<SysDict>();

			// 取出字典大类
			String hql = " from SysDict s where s.enabled=1 and s.parentcode='0'";
			List<SysDict> list = super.getBaseDao().find(hql.toString());
			if (list == null || list.size() < 1)
				return;
			// 循环读出各大类下面的字典明细
			for (SysDict sysDict : list) {
				hql = " from SysDict s where s.enabled=1 and s.parentcode='"
						+ sysDict.getDictcode() + "' order by s.dictid ";
				List<SysDict> items = super.getBaseDao().find(hql.toString());
				if (items == null || items.size() < 1)
					continue;
				List entryList = new ArrayList();
				// 将字段明细封装至entry数据结构中
				for (SysDict dict : items) {
					entryList.add(new Entry(dict.getDictkey(), dict
							.getDictname()));
				}
				DataCacheManager.putDictItem(sysDict.getDictcode(), entryList);
			}
			System.out.println("加载数据缓存成功。");
		} catch (Exception e) {
			System.out.println("加载数据缓存失败。");
			e.printStackTrace();
		}
	}

}
