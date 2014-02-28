package com.bluecloud.framework.core.mvc.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.bluecloud.framework.core.mvc.base.dao.QueryPara;
import com.bluecloud.framework.core.mvc.base.dao.SortPara;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;

public interface IBaseService<E> {

	public String getSQL(String sqlId, Map<String, Object> pars) throws Exception;
	
	public boolean isNullOrEmpty(String s);
	
	
	/**
	 * 新增一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void insertObj(Object obj) throws Exception;
	
	/**
	 * 修改一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void updateObj(String pk,Object obj,String[] cols) throws Exception;
	
	/**
	 * 根据主键物理删除一条记录
	 * @param obj
	 * @throws Exception
	 */
	public void deleteObj(String pk) throws Exception;
	
	/**
	 * 根据主键查询一条记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object queryObjByPK(String pk) throws Exception;
	
	public Long getPrimarykey(String seqname) throws Exception;
	
	public List<E> findAllByPage(String mainHql, List<QueryPara> queryParas, List<SortPara> sortParas, PaginationSupport paginationSupport);
	
	/**
	 * 解析逗号隔开Id串
	* <p>方法名称: toTranslateIds|描述: </p>
	* @param Ids
	* @return
	 */
	public String toTranslateString(String str);
	
	/**
	 * 解析逗号隔开Id串
	* <p>方法名称: toTranslateIds|描述: </p>
	* @param Ids
	* @return
	 */
	public String toTranslateIds(String Ids);
	
	public E findById(Serializable id);
	
	public void insert(E entity);
	
	public void deleteAllByIds(Serializable[] ids);
	
	public void update(E entity);
}
