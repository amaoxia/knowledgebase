package com.bluecloud.component.sys.modules.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bluecloud.component.cache.DataCacheManager;
import com.bluecloud.component.sys.entity.po.SysDict;
import com.bluecloud.framework.Entry;
import com.bluecloud.framework.core.mvc.base.BaseService;

@Service
public class DictService extends BaseService {

	public List<SysDict> loadDictList(SysDict sysDict) throws Exception {
		StringBuffer hql = new StringBuffer().append(" from SysDict s where s.parentcode='0' and s.enabled='1' ");
		if(sysDict!=null) {
			if (sysDict.getDictid() != null) {
				hql.append(" and s.dictid = " + sysDict.getDictid() + " ");
			}
			if (!super.isNullOrEmpty(sysDict.getDictcode())) {
				hql.append(" and s.dictcode = " + sysDict.getDictcode() + " ");
			}
			if (!super.isNullOrEmpty(sysDict.getDictkey())) {
				hql.append(" and s.dictkey like '%" + StringUtils.replace(sysDict.getDictkey().trim(), "'", "''") + "%' ");
			}
			if (!super.isNullOrEmpty(sysDict.getDictname())) {
				hql.append(" and s.dictname like '%" + StringUtils.replace(sysDict.getDictname().trim(), "'", "''") + "%' ");
			}
		}
		hql.append(" order by s.createtime desc ");
		return super.getBaseDao().find(hql.toString());
	}

	/**
	 * 新增一个类型
	 * 
	 * @param Dict
	 * @return
	 * @throws Exception
	 */
	public String insertDict(SysDict dict) throws Exception {
		String msg = "";
		try {
			getBaseDao().save(dict);
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	/**
	 * 修改大类信息
	 * 
	 * @param dict
	 * @return
	 * @throws Exception
	 */
	public String updateDict(SysDict dict) throws Exception {
		String msg = "";
		try {
			getBaseDao().update(dict);
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	/**
	 * 删除大类信息
	 * 
	 * @param dict
	 * @return
	 * @throws Exception
	 */
	public String delDict(String Ids) throws Exception {
		String msg = "";
		try {
			String[] array = Ids.split(",");
			SysDict dict = new SysDict();
			for (int i = 0; i < array.length; i++) {
				dict.setDictid(Long.valueOf(array[i]));
				SysDict sysdict = new SysDict();
				sysdict = this.loadSysDict(dict,null);
				List entryList=new ArrayList();//修改缓存
				DataCacheManager.putDictItem(sysdict.getDictkey(), entryList);
				String hqls = " delete from SysDict s where s.parentcode = '" + sysdict.getDictcode() + "' ";
				getBaseDao().bulkUpdate(hqls);
			}
			String hql = " delete from SysDict s where s.dictid in(" + super.toTranslateString(Ids) + ")";
			getBaseDao().bulkUpdate(hql);
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	public String delDictItems(String Ids) throws Exception {
		String msg = "";
		try {
			if(!super.isNullOrEmpty(Ids)) {//刷新缓存
				SysDict sysdict = new SysDict();
				sysdict.setDictid(Long.valueOf(Ids));
				sysdict = this.loadSysDict(sysdict,null);
				SysDict dict = new SysDict();
				dict.setDictcode(sysdict.getParentcode());
				dict = this.loadSysDict(dict,null);
				List<SysDict> items = this.loadDictDetailList(dict);
				if(items==null || items.size()<1) return null;
				List entryList=new ArrayList();
				//将字段明细封装至entry数据结构中
				for(SysDict entity : items){
					entryList.add(new Entry(entity.getDictkey(),entity.getDictname()));					
				}
				DataCacheManager.putDictItem(sysdict.getDictkey(), entryList);
			}
			String hql = " delete from SysDict s where s.dictid in(" + super.toTranslateString(Ids) + ")";
			getBaseDao().bulkUpdate(hql);
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	public SysDict loadSysDict(SysDict sysDict,String parentcode) {
		StringBuffer sbhql = new StringBuffer().append(" from SysDict s  where 1=1 ");
		if(sysDict!=null) {
			if (!super.isNullOrEmpty(sysDict.getDictcode())) {
				sbhql.append(" and s.dictcode = '" + StringUtils.replace(sysDict.getDictcode().trim(), "'", "''") + "'");
			}
			if (sysDict.getDictid() != null) {
				sbhql.append(" and s.dictid = '" + sysDict.getDictid() + "'");
			}
			if (sysDict.getDictkey() != null) {
				sbhql.append(" and s.dictkey = '" + StringUtils.replace(sysDict.getDictkey().trim(), "'", "''") + "'");
			}
		}
		if(!super.isNullOrEmpty(parentcode)) {
			sbhql.append(" and s.parentcode = '+parentcode+'");
		}
		List<SysDict> list = super.getBaseDao().find(sbhql.toString());
		if (list == null || list.size() <= 0)
			return null;
		else
			return list.get(0);
	}


	public List<SysDict> loadDictDetailList(SysDict sysDict) {
		StringBuffer hql = new StringBuffer().append(" from SysDict s where 1=1 ");
		if (sysDict.getDictid() != null) {
			hql.append(" and s.dictid = " + sysDict.getDictid() + " ");
		}
		if (!super.isNullOrEmpty(sysDict.getParentcode())) {
			hql.append(" and s.parentcode = '" + sysDict.getParentcode() + "' ");
		}
		hql.append(" order by s.createtime desc ");
		return super.getBaseDao().find(hql.toString());
	}
	
}
