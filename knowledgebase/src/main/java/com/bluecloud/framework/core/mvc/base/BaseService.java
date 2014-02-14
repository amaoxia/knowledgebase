package com.bluecloud.framework.core.mvc.base;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bluecloud.framework.core.mvc.sqlmanager.SQLManager;

/**
 *<p>公共业务逻辑服务类</p>
 * @author dafei
 *
 */
public class BaseService 
{
	@Resource(name = "baseDao")
	private BaseHibernateDao baseDao;

	public BaseHibernateDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseHibernateDao baseDao) {
		this.baseDao = baseDao;
	}

	public String getSQL(String sqlId,Map pars) throws Exception{
		return SQLManager.getSql(sqlId,pars);
	}
	
	public boolean isNullOrEmpty(String s){
		return s==null || "".equals(s);
	}
	
	
	/**
	 * 新增一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void insertObj(Object obj) throws Exception
	{
		getBaseDao().save(obj);
	}
	
	/**
	 * 修改一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void updateObj(String pk,Object obj,String[] cols) throws Exception
	{		
		getBaseDao().update(pk,obj,cols);
	}
	
	/**
	 * 根据主键物理删除一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void deleteObj(String pk) throws Exception
	{
		getBaseDao().deleteByKey(pk);
	}
	
	/**
	 * 根据主键查询一条记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object queryObjByPK(String pk) throws Exception
	{
		return getBaseDao().get(pk);
	}
	
	public Long getPrimarykey(String seqname) throws Exception{
		return getBaseDao().querySeqByName(seqname);
	}
	
	/**
	 * 解析逗号隔开Id串
	* <p>方法名称: toTranslateIds|描述: </p>
	* @param Ids
	* @return
	 */
	public String toTranslateString(String str) 
	{
		String[] IdsArray = str.split(",");
		if(IdsArray==null || IdsArray.length<1) return str;
		String strIds = "";
		for(String s : IdsArray) {
			strIds += "'"+s+"',";
		}
		strIds = strIds.substring(0, strIds.length()-1);
		return strIds;
	}
}
