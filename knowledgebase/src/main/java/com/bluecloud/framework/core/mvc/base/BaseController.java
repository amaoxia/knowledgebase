package com.bluecloud.framework.core.mvc.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import com.bluecloud.component.sys.entity.po.SysUser;
import com.bluecloud.framework.AppConstant;
import com.bluecloud.framework.AppUtils;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;
import com.bluecloud.framework.core.security.bean.UserDetailsEX;

/**
 * 抽象控制器类
 * 
 * @author dafei
 */
public abstract class BaseController {

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
}
