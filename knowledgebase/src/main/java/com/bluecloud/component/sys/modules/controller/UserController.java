package com.bluecloud.component.sys.modules.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluecloud.component.sys.entity.po.SysOrg;
import com.bluecloud.component.sys.entity.po.SysUser;
import com.bluecloud.component.sys.modules.service.UserService;
import com.bluecloud.framework.core.mvc.base.controller.BaseController;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;

@Controller
public class UserController extends BaseController {
	private static final String USER_LIST = "system/userList";
	private static final String USER_ADD = "system/userAdd2";
	private static final String USER_EDIT = "system/userEdit";
	@Resource(name = "userService")
	UserService userService;
	
	@RequestMapping(value = "/login")
	public String test(HttpServletRequest request,
			HttpServletResponse response, SysUser userinfo) { // 非常方便可以直接在方法里面放入对象
		if (userinfo.getUsername().equals("aaa")
				&& userinfo.getLoginpwd().equals("ok")) {
			request.setAttribute("userinfo", userinfo);
			return "users/loginok"; // 判断，将跳转不同的页面
		} else {
			return "users/loginerr"; // 判断，将跳转不同的页面
		}
	}
	
	/**
	 * 用户信息查询列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/user" )
	public String doUserList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = USER_LIST;
		String username=request.getParameter("user.username");
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		try {
			PaginationSupport pager = userService.loadUserList(sysUser,getPager(request));
			request.setAttribute("pagerResult", pager);
			request.setAttribute("username", username);
		} catch(Exception e){
			e.printStackTrace();
		}
		return forward;		
	}

	/**
	 * 跳转到添加或修改页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/userToEdit" )
	public String toEditUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = USER_ADD;
		String Ids = request.getParameter("Ids");
		if(!super.isEmptyOrNull(Ids)) {
			forward = USER_EDIT;
			SysUser entity = new SysUser();
			entity.setId(Long.valueOf(Ids));
			SysUser sysUser = userService.loadSysUser(entity);
			SysOrg sysOrg = new SysOrg();
			sysOrg.setId(sysUser.getOrgid());
			sysOrg = userService.loadOrg(sysOrg);
			request.setAttribute("sysUser", sysUser);
			request.setAttribute("sysOrg", sysOrg);
		}
		return forward;	
	}
	/*
	 * 添加用户信息
	 */
	@RequestMapping(value = "/sys/userAdd", method = RequestMethod.POST)
	public String doAddUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String responseString = "true";
		SysUser sysUser = new SysUser();
		sysUser = this.setUserEntity(sysUser, request);
		String orgcode = request.getParameter("user.orgid");
		if(!super.isEmptyOrNull(orgcode)) {//机构代码
			SysOrg entity = new SysOrg();
			entity.setOrgcode(orgcode);
			entity = userService.loadOrg(entity);
			sysUser.setOrgid(entity.getId());
		}
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currTime = formatter.format(date);
		sysUser.setCreatetime(currTime);
		try{
			userService.insertObj(sysUser);
		}catch(Exception e) {
			responseString = "false";
			e.printStackTrace();
		}finally {
			response.getWriter().print(responseString);
		}
		return null;	
	}
	/**
	 * 修改用户信息
	 */
	@RequestMapping(value = "/sys/userEdit", method = RequestMethod.POST)
	public String doEditUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String responseString = "true";
		SysUser entity = new SysUser();
		entity.setId(Long.valueOf(request.getParameter("userid")));
		SysUser sysUser = userService.loadSysUser(entity);
		sysUser = this.setUserEntity(sysUser, request);
		if(!super.isEmptyOrNull(request.getParameter("user.orgid"))) {
			sysUser.setOrgid(Long.valueOf(request.getParameter("user.orgid")));
		}
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currTime = formatter.format(date);
		
		sysUser.setEdittime(currTime);
		try{
			userService.updateUser(sysUser);
		}catch(Exception e) {
			responseString = "false";
			e.printStackTrace();
		}finally {
			response.getWriter().print(responseString);
		}
		return null;	
	}
	/**
	 * 逻辑删除用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/userDelete", method = RequestMethod.POST)
	public String doDelUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
		userService.delUser(request.getParameter("Ids"));
		return doUserList(request,response);	
	}
	/**
	 * 核查重复用户账户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/userCheck", method = RequestMethod.POST)
	public String checkRepeat(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String responseString = "true";
		try{
			SysUser sysUser = new SysUser();
			sysUser.setLoginuser((request.getParameter("user.loginuser")));
			SysUser entity = userService.loadSysUser(sysUser);
			if(entity != null) responseString = "false";
		}catch(Exception e) {
			responseString = "false";
			e.printStackTrace();
		}finally {
			response.getWriter().print(responseString);
		}
		return null;	
	}
	/**
	 * 返回
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/userBack", method = RequestMethod.POST)
	public String toGoBack(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return doUserList(request,response);
	}
	/**
	 * 取得页面参数存入实体
	 * @param sysUser
	 * @param request
	 * @return
	 */
	private SysUser setUserEntity(SysUser sysUser, HttpServletRequest request) {
		sysUser.setUsername(request.getParameter("user.username"));//用户名称
		sysUser.setUsercode(request.getParameter("user.usercode"));//用户编号
		//sysUser.setPostid(Long.valueOf(request.getParameter("user.postid")));
		sysUser.setLoginuser(request.getParameter("user.loginuser"));//用户账号
		sysUser.setLoginpwd(request.getParameter("user.loginpwd"));//用户密码
		sysUser.setUseremail(request.getParameter("user.useremail"));
		sysUser.setUsertel(request.getParameter("user.usertel"));
		sysUser.setUsermp(request.getParameter("user.usermp"));
		sysUser.setUserjob(request.getParameter("user.userjob"));
		sysUser.setUserqq(request.getParameter("user.userqq"));
		if(!super.isEmptyOrNull(request.getParameter("user.usertype"))) {
			sysUser.setUsertype(request.getParameter("user.usertype"));
		}
		sysUser.setUsermsn(request.getParameter("user.usermsn"));
		if(!super.isEmptyOrNull(request.getParameter("user.usersex"))) {
			sysUser.setUsersex(Long.valueOf(request.getParameter("user.usersex")));
		}
		sysUser.setEnabled(Long.valueOf("1"));//是否有效1：有效，0：无效
		//sysUser.setEditer((String)getSession(request, "j_user"));
		return sysUser;
	}
	
}
