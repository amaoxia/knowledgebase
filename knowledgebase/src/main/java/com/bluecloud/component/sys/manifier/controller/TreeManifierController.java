package com.bluecloud.component.sys.manifier.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluecloud.component.sys.entity.po.SysOrg;
import com.bluecloud.component.sys.manifier.service.TreeManifierService;
import com.bluecloud.framework.core.mvc.base.BaseController;

@Controller
public class TreeManifierController extends BaseController {
	@Resource(name = "treeManifierService")
	TreeManifierService treeManifierService;

	@RequestMapping(value = "/sys/openOrgTree")
	public String openOrgTree(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = "system/openOrgTree";
		SysOrg sysOrg = new SysOrg();
		List<SysOrg> sysOrgList = treeManifierService.getSysOrgTreeList(sysOrg);
		sysOrg.setOrgcode("999");
		request.setAttribute("sysOrg", sysOrg);
		request.setAttribute("orgTree", markOrgTreeString(sysOrgList));
		return forward;
	}
	
	private String markOrgTreeString(List<SysOrg> orgList)
	throws Exception {
		String sTree = "";
		if (orgList == null || orgList.size() < 1)
			return "";
		for (int i = 0; i < orgList.size(); i++) {
			SysOrg sysOrg = orgList.get(i);
			if(super.isEmptyOrNull(sysOrg.getParentcode())) {
				sysOrg.setParentcode("999");
			}
			sTree += sysOrg.getOrgcode() + "," + sysOrg.getParentcode() + ","
					+ sysOrg.getOrgname() + ";";
			sTree = "999,-1,root;" + sTree;
		}
		return sTree;
	}
}
