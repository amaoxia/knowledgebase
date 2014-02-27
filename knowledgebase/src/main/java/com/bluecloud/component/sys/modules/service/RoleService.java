package com.bluecloud.component.sys.modules.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bluecloud.component.sys.entity.po.SysRole;
import com.bluecloud.framework.core.mvc.base.service.impl.BaseServiceImpl;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;

@Service("roleService")
public class RoleService extends BaseServiceImpl {
	
	/**
	 * 新增一个用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String insertRole(SysRole sysRole) throws Exception {
		String msg = "";
		try {
			insertObj(sysRole);
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String updateRole(SysRole sysRole) throws Exception {
		String msg = "";
		try {
			getBaseDao().update(sysRole);
			//updateObj(sysRole.getRoleid()+"", sysRole, new String[]{});
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}

	/**
	 * 删除用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String delRole(SysRole sysRole) throws Exception {
		String msg = "";
		try {
			deleteObj(sysRole.getId()+"");
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}
	
	public String delRoles(String Ids) throws Exception {
		String msg = "";
		try {
			getBaseDao().bulkUpdate("delete SysRole r where r.id in("+Ids+")");
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}
	
	public PaginationSupport getRoleList(SysRole sysRole, PaginationSupport pager) throws Exception{
		StringBuffer sbHql = new StringBuffer().append(" from SysRole r where r.enabled='1' ");
		if(sysRole!=null){
			if(!isNullOrEmpty(sysRole.getRolename())){
				sbHql.append(" and r.rolename like '%").append(sysRole.getRolename()).append("%'");
			}
		}
		return getBaseDao().loadPageResultByHQuery(sbHql.toString(), pager.getPageSize(), pager.getStartIndex(), null);
	}
	
	public SysRole getRole(SysRole sysRole) throws Exception{
		String hql=" from SysRole r where r.enabled='1' ";
		if(sysRole!=null){
			if(sysRole.getId()!=null&&!"".equals(sysRole.getId())){
				hql += " and r.id="+sysRole.getId();
			}
		}
		List<SysRole> sysRoleList = getBaseDao().find(hql);
		if(sysRoleList==null||sysRoleList.size()<=0)return sysRole;
		return sysRoleList.get(0);
	}
	
	public SysRole getRoleByPrimkey(String roleid) throws Exception{
		return (SysRole)queryObjByPK(roleid);
	}
}
