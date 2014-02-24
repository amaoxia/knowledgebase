package com.bluecloud.component.sys.modules.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluecloud.component.sys.entity.po.SysOrg;
import com.bluecloud.component.sys.modules.service.OrgService;
import com.bluecloud.framework.core.mvc.base.controller.BaseController;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;
import com.bluecloud.framework.utils.MiscUtil;

@Controller
public class OrgController extends BaseController {
	@Resource(name = "orgService")
	OrgService orgService;

	@RequestMapping(value = "/sys/org")
	public String doSysOrgList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgasname(request.getParameter("orgasname"));
		PaginationSupport pager = orgService.getSysOrgList(sysOrg, getPager(request));
		request.setAttribute("pagerResult", pager);
		return "system/orgList";
	}

	@RequestMapping(value = "/sys/toAddOrg")
	public String toAddSysOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return "system/orgAdd";
	}
	
	@RequestMapping(value = "/sys/doAddOrg")
	public String doAddSysOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setScode(request.getParameter("orgcode"));
		sysOrg.setOrgcode(request.getParameter("orgcode"));
		if(!isEmptyOrNull(request.getParameter("parentcode")))sysOrg.setParentcode(request.getParameter("parentcode"));
		sysOrg.setOrgtype(request.getParameter("orgtype"));
		sysOrg.setOrgname(request.getParameter("orgname"));
		sysOrg.setOrgasname(request.getParameter("orgasname"));
		sysOrg.setOrgfax(request.getParameter("orgfax"));
		sysOrg.setOrgtel(request.getParameter("orgtel"));
		sysOrg.setOrgaddress(request.getParameter("orgaddress"));
		String currTime = MiscUtil.formatDate(new Date(), 7);
		sysOrg.setCreater(getLoginUserInfo().getLoginuser());
		sysOrg.setCreatetime(currTime);
		sysOrg.setEditer(getLoginUserInfo().getLoginuser());
		sysOrg.setEdittime(currTime);
		sysOrg.setEnabled(new Long("1"));
		
		orgService.insertSysOrg(sysOrg);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("{success:\"true\"}");
		return null;
	}

	@RequestMapping(value = "/sys/toEditOrg")
	public String toEditSysOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//SysRole sysRole = roleService.getRoleByPrimkey(roleid);
		SysOrg sysOrg = new SysOrg();
		if(!isEmptyOrNull(request.getParameter("orgid")))sysOrg.setId(new Long(request.getParameter("orgid")));
		if(!isEmptyOrNull(request.getParameter("scode")))sysOrg.setScode(request.getParameter("scode"));
		SysOrg entity = orgService.getSysOrg(sysOrg);
		request.setAttribute("sysOrg", entity);
		return "system/orgEdit";
	}
	
	@RequestMapping(value = "/sys/toViewOrg")
	public String toViewSysOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//SysRole sysRole = roleService.getRoleByPrimkey(roleid);
		SysOrg sysOrg = new SysOrg();
		if(!isEmptyOrNull(request.getParameter("orgid")))sysOrg.setId(new Long(request.getParameter("orgid")));
		if(!isEmptyOrNull(request.getParameter("scode")))sysOrg.setScode(request.getParameter("scode"));
		SysOrg entity = orgService.getSysOrg(sysOrg);
		request.setAttribute("sysOrg", entity);
		return "system/orgView";
	}
	
	@RequestMapping(value = "/sys/doEditOrg")
	public String doEditSysOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setId(new Long(request.getParameter("orgid")));
		SysOrg entity = orgService.getSysOrg(sysOrg);
		
		entity.setOrgcode(request.getParameter("orgcode"));
		entity.setOrgtype(request.getParameter("orgtype"));
		entity.setOrgname(request.getParameter("orgname"));
		entity.setOrgasname(request.getParameter("orgasname"));
		entity.setOrgfax(request.getParameter("orgfax"));
		entity.setOrgtel(request.getParameter("orgtel"));
		entity.setOrgaddress(request.getParameter("orgaddress"));
		sysOrg.setEditer((String)getSession(request, "j_user"));
		sysOrg.setEdittime(MiscUtil.formatDate(new Date(), 7));
		
		//roleService.updateObj(roleid, sysRole, new String[]{"rolename", "roledesc"});
		orgService.updateSysOrg(entity);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("{success:\"true\"}");
		return null;
	}

	@RequestMapping(value = "/sys/doDelOrg")
	public String doDelSysOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		orgService.delSysOrgs(request.getParameter("scodes"));
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("{success:\"true\"}");
		return null;
	}
	
	@RequestMapping(value = "/sys/doDelOrgByCode")
	public String doDelOrgByCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
		orgService.delSysOrgByCode(request.getParameter("Ids"));
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("{success:\"true\"}");
		return null;
	}
	
	@RequestMapping(value = "/sys/toMaintainOrg")
	public String toMaintainSysOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setId(new Long(request.getParameter("orgid")));
		SysOrg entity = orgService.getSysOrg(sysOrg);
		request.setAttribute("sysOrg", entity);
		return "system/orgMaintain";
	}
	
	@RequestMapping(value = "/sys/toTreeOrg")
	public String toTreeSysOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setId(new Long(request.getParameter("orgid")));
		SysOrg entity = orgService.getSysOrg(sysOrg);
		
		sysOrg.setScode(request.getParameter("scode"));
		List<SysOrg> sysOrgList = orgService.getSysOrgTreeList(sysOrg);
		
		request.setAttribute("sysOrg", entity);
		request.setAttribute("orgTree", markOrgTreeString(sysOrgList));
		return "system/orgTree";
	}
	
	@RequestMapping(value = "/sys/doMaintainOrg")
	public String doMaintainSysOrg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysOrg sysOrg = new SysOrg();
		sysOrg.setId(new Long(request.getParameter("orgid")));
		SysOrg entity = orgService.getSysOrg(sysOrg);
		
		entity.setOrgname(request.getParameter("orgname"));
		entity.setOrgasname(request.getParameter("orgasname"));
		entity.setOrgfax(request.getParameter("orgfax"));
		entity.setOrgtel(request.getParameter("orgtel"));
		entity.setOrgaddress(request.getParameter("orgaddress"));
		sysOrg.setEditer((String)getSession(request, "j_user"));
		sysOrg.setEdittime(MiscUtil.formatDate(new Date(), 7));
		
		//roleService.updateObj(roleid, sysRole, new String[]{"rolename", "roledesc"});
		orgService.updateSysOrg(entity);
		return doSysOrgList(request, response);
	}
	
	private String markOrgTreeString(List<SysOrg> orgList)
	throws Exception {
		String sTree = "";
		if (orgList == null || orgList.size() < 1)
			return "";
		for (int i = 0; i < orgList.size(); i++) {
			SysOrg sysOrg = orgList.get(i);
			sTree += sysOrg.getScode() + "," + sysOrg.getParentcode() + ","
					+ sysOrg.getOrgname() + ";";
		}
		return sTree;
	}
}
