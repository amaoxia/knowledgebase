package com.bluecloud.framework.core.mvc.base.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bluecloud.framework.core.mvc.base.dao.BaseHibernateDao;
import com.bluecloud.framework.core.mvc.base.dao.QueryPara;
import com.bluecloud.framework.core.mvc.base.dao.SortPara;
import com.bluecloud.framework.core.mvc.base.service.IBaseService;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;
import com.bluecloud.framework.core.mvc.sqlmanager.SQLManager;

/**
 *<p>公共业务逻辑服务类</p>
 * @author dafei
 *
 */
public class BaseServiceImpl<E> implements IBaseService<E>{
	
	@Resource(name = "baseDao")
	private BaseHibernateDao baseDao;

	public BaseHibernateDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseHibernateDao baseDao) {
		this.baseDao = baseDao;
	}

	public String getSQL(String sqlId, Map<String, Object> pars) throws Exception{
		return SQLManager.getSql(sqlId, pars);
	}
	
	public boolean isNullOrEmpty(String s){
		return s==null || "".equals(s);
	}
	
	
	/**
	 * 新增一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void insertObj(Object obj) throws Exception {
		getBaseDao().save(obj);
	}
	
	/**
	 * 修改一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void updateObj(String pk,Object obj,String[] cols) throws Exception {		
		getBaseDao().update(pk,obj,cols);
	}
	
	/**
	 * 根据主键物理删除一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void deleteObj(String pk) throws Exception {
		getBaseDao().deleteByKey(pk);
	}
	
	/**
	 * 根据主键查询一条记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object queryObjByPK(String pk) throws Exception {
		return getBaseDao().get(pk);
	}
	
	public Long getPrimarykey(String seqname) throws Exception {
		return getBaseDao().querySeqByName(seqname);
	}
	
	/**
	 * 解析逗号隔开Id串
	* <p>方法名称: toTranslateIds|描述: </p>
	* @param Ids
	* @return
	 */
	public String toTranslateString(String str){
		String[] IdsArray = str.split(",");
		if(IdsArray==null || IdsArray.length<1) return str;
		String strIds = "";
		for(String s : IdsArray) {
			strIds += "'"+s+"',";
		}
		strIds = strIds.substring(0, strIds.length()-1);
		return strIds;
	}
	
	/**
	 * 解析逗号隔开Id串
	* <p>方法名称: toTranslateIds|描述: </p>
	* @param Ids
	* @return
	 */
	public String toTranslateIds(String Ids) {
		String[] IdsArray = Ids.split(",");
		String strIds = "";
		/*if(IdsArray.length==1) {
			return IdsArray[0];
		}*/
		for(String Id : IdsArray) {
			strIds += "'"+Id+"',";
		}
		strIds = strIds.substring(0, strIds.length()-1);
		return strIds;
	}
	@Override
	public List<E> findAllByPage(String mainHql, List<QueryPara> queryParas,
			List<SortPara> sortParas, PaginationSupport paginationSupport) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public E findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insert(E entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAllByIds(Serializable[] ids) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(E entity) {
		// TODO Auto-generated method stub
		
	}
}
