package com.bluecloud.framework.core.mvc.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import com.bluecloud.component.sys.entity.po.SysUser;
import com.bluecloud.framework.AppConstant;
import com.bluecloud.framework.AppUtils;
import com.bluecloud.framework.core.mvc.base.dao.Constants;
import com.bluecloud.framework.core.mvc.base.dao.QueryPara;
import com.bluecloud.framework.core.mvc.base.dao.RequestHelper;
import com.bluecloud.framework.core.mvc.base.dao.SortPara;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;
import com.bluecloud.framework.core.security.bean.UserDetailsEX;

/**
 * 抽象控制器类
 * 
 * @author dafei
 */
public abstract class BaseController<E> {

	public final static String PAGER_RESULT_KEY = "pagerResult";

	final static String PAGE_REDDICT = "/common/redict.jsp";

	// 转到指定页
	public void forward(String url, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * 重定向方法
	 * 
	 * @param url
	 * @param response
	 * @throws Exception
	 */
	public void redictPage(String url, HttpServletResponse response)
			throws Exception {
		url = "?targetUrl=" + url;
		response.sendRedirect(PAGE_REDDICT + url);
	}

	/**
	 * 获取分页属性值
	 * 
	 * @param req
	 * @return
	 */
	public PaginationSupport getPager(HttpServletRequest req) {
		PaginationSupport pager = new PaginationSupport(null, 0);
		if (isEmptyOrNull(req.getParameter("pageSize")) == false) {
			pager.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
		}
		if (isEmptyOrNull(req.getParameter("startIndex")) == false) {
			pager.setStartIndex(Integer
					.parseInt(req.getParameter("startIndex")));
		}
		return pager;
	}

	/**
	 * 获取配置文件信息
	 * 
	 * @param key
	 * @return
	 */
	public String getConfigByKey(String key) {
		return AppConstant.getConfig(key);
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @return
	 */
	public SysUser getLoginUserInfo() {
		UserDetailsEX userEx = (UserDetailsEX) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		if (userEx == null || userEx.getUserObj() == null)
			return null;
		return (SysUser) userEx.getUserObj();
	}

	public String[] getRolesForLoginUser() {
		UserDetailsEX userEx = (UserDetailsEX) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		if (userEx == null || userEx.getUserObj() == null)
			return null;
		return userEx.getRolesForLoginUser();
	}

	/**
	 * 获取session中值
	 * 
	 * @param req
	 * @param key
	 * @return
	 */
	public Object getSession(HttpServletRequest req, String key) {
		return AppUtils.getSession(req, key);
	}

	/**
	 * 获取国际化资源消息
	 * 
	 * @param key
	 * @param args
	 * @return
	 */
	public static String getMessage(String key, String[] args) {
		return AppUtils.getMessage(key, args);
	}

	public boolean isEmptyOrNull(String s) {
		return s == null || "".equals(s);
	}
	
	protected void setQueryAndsort(HttpServletRequest request) {
		List<QueryPara> queryParas = getQueryParas(request);
		List<SortPara> sortParas = getSortParas(request);
		Map<String, String> serchMap = getSerchMap(request);
		
		queryParas.addAll(RequestHelper
				.getParametersWithCondition(request));// 在页面页面上的格式conditions[age,>=,int]
		sortParas.addAll(RequestHelper
				.getOrderParametersWithOrder(request));// 在页面页面上的格式order[age]

		serchMap.putAll(RequestHelper.getParametersForMap(
				request, RequestHelper.CONDITION)); // 将条件的属性和值写到serchMap中
		serchMap.putAll(RequestHelper.getParametersForMap(
				request, RequestHelper.ORDER));// 将排序的属性和值的serchMap中

		queryParas.addAll(RequestHelper
				.getParametersWithPrefix(request));// search_
		sortParas.addAll(RequestHelper
				.getOrderParametersWithPrefix(request));// order_

		serchMap.putAll(RequestHelper.getParametersForMap(
				request, RequestHelper.SEARCHPREFIX));// 将条件的值写的serchMap中
		serchMap.putAll(RequestHelper.getParametersForMap(
				request, RequestHelper.ORDERPREFIX));// 将排序的属性和值的serchMap中

		Map<String, String> paraMap = RequestHelper.prefixedMapToMap(serchMap); // [e.loginName,string,like]=1
		// 转换成e_loginName=1
		if (sortParas.size() == 0) {
			sortParas.add(new SortPara("e.id", Constants.DESC));// 初始以主键ID排序
		}

		serchMap.clear();
		serchMap.putAll(paraMap);
	}
	
	/**
	 * 用于查询和分页显示
	 * 
	 * @author hudaowan
	 * @date 2010-9-15 下午01:55:23
	 * @return
	 */
	protected List<E> getAllObjectBypage(HttpServletRequest request) {
		List<E> list = new ArrayList<E>();
		list = getEntityService().findAllByPage(getSelectStr(), getQueryParas(request),
				getSortParas(request), getPaginationSupport(request));
		return list;
	}

	/**
	 * 生成对象查询语句
	 * 
	 * @author hudaowan
	 * @date 2010-9-15 下午01:55:23
	 * @return
	 */
	protected String getSelectStr() { // 获取mainSql
		return "from " + getEntityClass().getSimpleName() + " e ";
	}

	/**
	 * 从request中把参数组装到域对象中
	 * 
	 * @author huwanshan
	 * @date 2010-12-12 下午09:31:56
	 * @param request
	 * @return
	 */
	protected E popValueFromRequest(HttpServletRequest request) {
		E obj = doNewEntity();
		Map<String, Object> source = getParamMap(request);
		mapperValue.map(source, obj);
		return obj;
	}
	
	protected PaginationSupport getPaginationSupport(HttpServletRequest request){
		Object paginationSupport = request.getAttribute("paginationSupport");
		if(paginationSupport==null)paginationSupport = new PaginationSupport(null, 0);
		return (PaginationSupport)paginationSupport;
	}

	protected List<E> getListDatas(HttpServletRequest request){
		Object listDatas = request.getAttribute("listDatas");
		if(listDatas==null)listDatas = new ArrayList<E>();
		return (List<E>)listDatas;
	}

	protected List<QueryPara> getQueryParas(HttpServletRequest request){// 查询的条件
		Object queryParas = request.getAttribute("queryParas");
		if(queryParas==null)queryParas = new ArrayList<QueryPara>();
		return (List<QueryPara>)queryParas;
	}

	protected List<SortPara> getSortParas(HttpServletRequest request){// 排序的条件
		Object sortParas = request.getAttribute("sortParas");
		if(sortParas==null)sortParas = new ArrayList<SortPara>();
		return (List<SortPara>)sortParas;
	}
	
	protected Map<String, String> getSerchMap(HttpServletRequest request){// 用于查询
		Object serchMap = request.getAttribute("serchMap");
		if(serchMap==null)serchMap = new HashMap<String, String>();
		return (Map<String, String>)serchMap;
	}
}
