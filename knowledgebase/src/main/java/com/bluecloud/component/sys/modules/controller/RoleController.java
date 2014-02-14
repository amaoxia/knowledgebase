package com.bluecloud.component.sys.modules.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluecloud.component.sys.entity.po.SysRole;
import com.bluecloud.component.sys.modules.service.RoleService;
import com.bluecloud.framework.core.mvc.base.BaseController;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;
import com.bluecloud.framework.utils.MiscUtil;

@Controller
public class RoleController extends BaseController {
	@Resource(name = "roleService")
	RoleService roleService;

	@RequestMapping(value = "/sys/role")
	public String doRoleList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysRole sysRole = new SysRole();
		sysRole.setRolename(request.getParameter("rolename"));
		PaginationSupport pager = roleService.getRoleList(sysRole, getPager(request));
		request.setAttribute("pagerResult", pager);
		return "system/roleList";
	}

	@RequestMapping(value = "/sys/toAddRole", method = RequestMethod.POST)
	public String toAddRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return "system/roleAdd";
	}
	
	@RequestMapping(value = "/sys/doAddRole", method = RequestMethod.POST)
	public String doAddRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysRole sysRole = new SysRole();
		sysRole.setRolename(request.getParameter("rolename"));
		sysRole.setRoledesc(request.getParameter("roledesc"));
		String currTime = MiscUtil.formatDate(new Date(), 7);
		sysRole.setCreater(getLoginUserInfo().getLoginuser());
		sysRole.setCreatetime(currTime);
		sysRole.setEditer(getLoginUserInfo().getLoginuser());
		sysRole.setEdittime(currTime);
		sysRole.setEnabled(new Long("1"));
		
		roleService.insertRole(sysRole);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("{success:\"true\"}");
		return null;
	}

	@RequestMapping(value = "/sys/toEditRole", method = RequestMethod.POST)
	public String toEditRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//SysRole sysRole = roleService.getRoleByPrimkey(roleid);
		SysRole sysRole = new SysRole();
		sysRole.setRoleid(new Long(request.getParameter("roleid")));
		SysRole entity = roleService.getRole(sysRole);
		request.setAttribute("sysRole", entity);
		
		return "system/roleEdit";
	}
	
	@RequestMapping(value = "/sys/doEditRole", method = RequestMethod.POST)
	public String doEditRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysRole sysRole = new SysRole();
		sysRole.setRoleid(new Long(request.getParameter("roleid")));
		SysRole entity = roleService.getRole(sysRole);
		
		//SysRole sysRole = new SysRole();
		entity.setRolename(request.getParameter("rolename"));
		entity.setRoledesc(request.getParameter("roledesc"));
		
		//roleService.updateObj(roleid, sysRole, new String[]{"rolename", "roledesc"});
		roleService.updateRole(entity);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("{success:\"true\"}");
		return null;
	}

	@RequestMapping(value = "/sys/doDelRole", method = RequestMethod.POST)
	public String doDelRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		roleService.delRoles(request.getParameter("Ids"));
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("{success:'true'}");
		return null;
	}
}
