package com.bluecloud.component.sys.security;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluecloud.component.sys.entity.po.SysUser;
import com.bluecloud.component.sys.security.service.SecurityService;
import com.bluecloud.framework.core.mvc.base.controller.BaseController;

/**
 * 系统公共安全入口类
 * <p>
 * 主要处理系统中的登录、退出及密码设置等操作
 * </p>
 * 
 * @author dafei
 * 
 */
@Controller
public class SecurityController extends BaseController {
	@Resource(name = "securityService")
	private SecurityService securityService;

	final static String PAGE_INDEX = "/common/home.jsp";

	// 登录页
	final static String PAGE_LOGIN = "/login.jsp";

	// 密码修改页
	final static String PAGE_PASSWORD_SET = "/common/passwordSet.jsp";

	//
	final static String PAGE_ERROR = "/common/error.jsp";

	/**
	 * 登录成功转到后台管理首面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String toIndexPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysUser user = (SysUser) super.getLoginUserInfo();
		if (user == null) {
			super.forward(PAGE_LOGIN, request, response);
			return null;
		}

		// 获取用户登录某个系统的菜单权限
		String syscode = getConfigByKey("sys.syscode");
		List menuList = securityService.loadRightsByLoginUser(user
				.getLoginuser(), syscode);
		request.setAttribute("menus", toJsonForMenuList(menuList));
		super.forward(PAGE_INDEX, request, response);
		return null;
	}
	
	@RequestMapping(value = "/index")
	public String toIndex(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysUser user = (SysUser) super.getLoginUserInfo();
		if (user == null) {
			super.forward(PAGE_LOGIN, request, response);
			return null;
		}

		// 获取用户登录某个系统的菜单权限
		String syscode = getConfigByKey("sys.syscode");
		List menuList = securityService.loadRightsByLoginUser(user
				.getLoginuser(), syscode);
		request.setAttribute("menus", toJsonForMenuList(menuList));
		return "home";
	}

	/**
	 * 转到密码修改页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/passwordSet")
	public String toPasswordSet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysUser user = (SysUser) super.getLoginUserInfo();
		if (user == null) {
			super.forward(PAGE_LOGIN, request, response);
			return null;
		}
		super.forward(PAGE_PASSWORD_SET, request, response);
		return null;
	}

	/**
	 * 保存密码的修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/passwordSave")
	public String savePassword(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysUser user = (SysUser) super.getLoginUserInfo();
		if (user == null) {
			super.forward(PAGE_LOGIN, request, response);
			return null;
		}
		String newpwd = request.getParameter("newpwd");
		if (newpwd.equals(user.getLoginpwd())) {
			// 保存新密码

			super.forward(PAGE_LOGIN, request, response);
			return null;
		}

		// 提示客户端旧密码与新密码不一致
		request.setAttribute("msg", super.getMessage("sys.setpassword.error",
				null));
		super.forward(PAGE_PASSWORD_SET, request, response);
		return null;
	}

	/**
	 * 退出转到登录页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginout")
	public String toLoginOutPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.forward(PAGE_LOGIN, request, response);
		return null;
	}

	/**
	 * 用于将重定向页的请求转向至分发请求
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/forwardPage")
	public String forwardPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getParameter("targeturl");
		super.forward(url, request, response);
		return null;
	}

	/**
	 * 将菜单集合转换为json字符串
	 * 
	 * @param menuList
	 * @return
	 * @throws Exception
	 */
	private String toJsonForMenuList(List menuList) throws Exception {
		return JsonMenuUtil.genSimpleDhtmlXTreeXml(menuList);
	}

}
