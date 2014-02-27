package com.bluecloud.component.sys.modules.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluecloud.framework.AppConstant;
import com.bluecloud.framework.core.mvc.base.controller.BaseController;
import com.bluecloud.framework.core.mvc.base.service.IBaseService;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;
import com.bluecloud.component.sys.entity.po.SysMenu;
import com.bluecloud.component.sys.entity.po.SysRole;
import com.bluecloud.component.sys.entity.po.SysUser;
import com.bluecloud.component.sys.entity.vo.SysUserVO;
import com.bluecloud.component.sys.modules.service.RightsService;
import com.bluecloud.component.sys.modules.service.RoleService;

@Controller
public class RightsController extends BaseController {
	private static final long serialVersionUID = 1L;

	@Resource(name = "rightsService")
	RightsService rightsService;
	
	@Resource(name = "roleService")
	RoleService roleService;

	@RequestMapping(value = "/sys/rights")
	public String doRoleList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysRole sysRole = new SysRole();
		sysRole.setRolename(request.getParameter("rolename"));
		PaginationSupport pager = roleService.getRoleList(sysRole, getPager(request));
		request.setAttribute("pagerResult", pager);
		return "system/rightsList";
	}
	

	/**
	 * 转到分配用户角色页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/toEditUserRole")
	public String toEditUserRole(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = "system/rightsUserList";
		String roleid = request.getParameter("roleid");
		SysRole sysRole = new SysRole();
		sysRole.setId(Long.valueOf(roleid));
		
		SysRole entity = roleService.getRole(sysRole);
		List<SysUserVO> list1 = rightsService.loadWFPUserForRole(new Long(roleid));
		List<SysUserVO> list2 = rightsService.loadYFPUserForRole(new Long(roleid));
		request.setAttribute("item1", list1);
		request.setAttribute("item2", list2);
		request.setAttribute("sysRole", entity);
		
		return forward;
	}

	/**
	 * 转到分配角色菜单页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/toEditRoleRights")
	public String toEditRoleRights(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = "system/rightsMenuList";
		String sysmark = AppConstant.SYSTEM_MARK_DEFAULT;
		
		if (!StringUtils.isEmpty(request.getParameter("sysmark"))) {
			sysmark = request.getParameter("sysmark");
		}
		
		String roleid = request.getParameter("roleid");
		SysRole sysRole = new SysRole();
		sysRole.setId(Long.valueOf(roleid));

		// 当前系统是否还包含其它子系统
		List syscodelist = (List) AppConstant.ConfigMap.get(AppConstant.SYSTEM_MARK);
		if (syscodelist != null && syscodelist.size() > 0) {
			request.setAttribute("sysmarks", syscodelist);
		}
		SysRole entity = roleService.getRole(sysRole);
		request.setAttribute("roleid", roleid);
		request.setAttribute("sysRole", entity);
		request.setAttribute(AppConstant.SYSTEM_MARK, sysmark);
		
		return forward;
	}

	/**
	 * 获得角色的权限与未分配权限
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/getRoleRights")
	public String getRoleRights(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = "system/rightsTreeRight";
		String flag = request.getParameter("flag");
		String roleid = request.getParameter("roleid");
		String sysmark = AppConstant.SYSTEM_MARK_DEFAULT;
		if (!StringUtils.isEmpty(request.getParameter("sysmark"))) {
			sysmark = request.getParameter("sysmark");
		}
		request.setAttribute("sysmark", sysmark);
		if ("left".equals(flag)) {
			List list1 = rightsService.loadLeftMenuForRole(sysmark, Long
					.valueOf(roleid));
			request.setAttribute("item1", markRightsTreeString(list1));
			forward = "system/rightsTreeLeft";
		} else {
			List list2 = rightsService.loadRightMenuForRole(sysmark, Long
					.valueOf(roleid));
			request.setAttribute("item2", markRightsTreeString(list2));
		}
		
		return forward;
	}

	/**
	 * 新增、移除用户角色角色操作方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/editUserForRights")
	public String editUserForRights(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String userIds = request.getParameter("Ids");
		String flag = request.getParameter("flag");
		String roleid = request.getParameter("roleid");
		boolean isAdd = "1".equals(flag);
		rightsService.updateUserRoleForRights(new Long(roleid), userIds, isAdd);
		return toEditUserRole(request, response);
	}

	/**
	 * 新增、移除角色菜单操作方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/editMenuForRights")
	public String editMenuForRights(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flag = request.getParameter("flag");
		String menuIds = request.getParameter("Ids");
		String sysmark = AppConstant.SYSTEM_MARK_DEFAULT;
		String roleId = request.getParameter("roleid");
		if (!StringUtils.isEmpty(request.getParameter("sysmark"))) {
			sysmark = request.getParameter("sysmark");
		}
		try {
			if ("addMenus".equals(flag)) {
				rightsService.addRightsForRole(sysmark, new Long(roleId), menuIds);
			} else {
				rightsService.delRightsForRole(sysmark, new Long(roleId), menuIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return toEditRoleRights(request, response);
	}
	
	private String markRightsTreeString(List<SysMenu> menuList)
	throws Exception {
		String sTree = "";
		if (menuList == null || menuList.size() < 1)
			return "";
		for (int i = 0; i < menuList.size(); i++) {
			SysMenu menu = menuList.get(i);
			sTree += menu.getMenucode() + "," + menu.getParentcode() + ","
					+ menu.getMenuname() + ";";
		}
		return sTree;
	}


	@Override
	protected IBaseService getEntityService() {
		return rightsService;
	}
}
