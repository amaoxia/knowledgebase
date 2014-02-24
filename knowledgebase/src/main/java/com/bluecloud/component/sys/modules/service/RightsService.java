package com.bluecloud.component.sys.modules.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bluecloud.framework.core.mvc.base.service.BaseService;
import com.bluecloud.component.sys.entity.po.SysMenu;
import com.bluecloud.component.sys.entity.po.SysRoleMenu;
import com.bluecloud.component.sys.entity.po.SysUser;
import com.bluecloud.component.sys.entity.po.SysUserRole;
import com.bluecloud.component.sys.entity.vo.SysUserVO;

@Service
public class RightsService extends BaseService {

	public List loadMenuRightsByUser(String sysmark, SysUser user)
			throws Exception {
		List list = new ArrayList();
		try {
			String roleIds = "";
			if ("admin".equals(user.getLoginuser().toLowerCase())) {
				return loadSuperRights(sysmark);
			}

			List<SysUserRole> rolelist = getBaseDao().find(
					"from SysUserRole ur where ur.id=" + user.getId());
			if (rolelist == null || rolelist.size() < 1)
				return list;
			for (int i = 0; i < rolelist.size(); i++) {
				roleIds += rolelist.get(i).getRoleid().longValue() + ",";
			}
			roleIds = roleIds.substring(0, roleIds.length() - 1);
			list = loadMenusForRoles(sysmark, roleIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	public List loadSuperRights(String sysmark) throws Exception {
		/*String sql = " select t.* from h_sys_menu t where t.sysmark='"
				+ sysmark + "' and t.menuenable=1 order by t.sequ";
		return getBaseDao().loadResultsForSql(sql,
				SysMenu.class.getName());*/
		String hql = "from h_sys_menu t where t.sysmark='"
				+ sysmark + "' and t.menuenable=1 order by t.sequ";
		return getBaseDao().find(hql);
	}

	/**
	 * 
	 * @param roleId
	 */
	public List loadWFPUserForRole(Long roleId) throws Exception {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append(" select u.id,u.loginuser,u.usercode,u.username, o.orgname from sys_user u, sys_org o ")
		.append(" where u.orgid = o.id and u.enabled = 1 ")
		.append("   and not exists(select ur.userid from sys_user_role ur where ur.roleid ="+roleId+")");		
		return getBaseDao().getResultList(sbSql.toString(), SysUserVO.class);
	}

	/**
	 * @param roleId
	 */
	public List loadYFPUserForRole(Long roleId) throws Exception {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append(" select u.id,u.loginuser,u.usercode,u.username, o.orgname from sys_user u ,sys_org o,sys_user_role ur ")
		.append(" where u.orgid = o.id and u.enabled=1 ")
		.append("   and u.id=ur.userid and ur.roleid="+roleId);
		return getBaseDao().getResultList(sbSql.toString(), SysUserVO.class);
	}

	/**
	 * 
	 * @param roleId
	 * @param userIds
	 * @param isAdd
	 *            true
	 * @throws Exception
	 */
	public void updateUserRoleForRights(Long roleId, String userIds,
			boolean isAdd) throws Exception {
		String[] arrUserId = userIds.split(",");
		if (isAdd) {
			for (int i = 0; i < arrUserId.length; i++) {
				if (super.isNullOrEmpty(arrUserId[i]))
					continue;
				SysUserRole ur = new SysUserRole();
				ur.setRoleid(Long.valueOf(roleId));
				ur.setUserid(Long.valueOf(arrUserId[i]));
				getBaseDao().save(ur);
			}
		} else {
			String ids = "";
			for (int i = 0; i < arrUserId.length; i++) {
				if (super.isNullOrEmpty(arrUserId[i]))
					continue;
				ids += arrUserId[i] + ",";
			}
			if (super.isNullOrEmpty(ids))
				return;
			ids = ids.substring(0, ids.length() - 1);
			String hql = " delete from SysUserRole ur where ur.userid in("
					+ ids + ") and ur.roleid=" + roleId;
			getBaseDao().bulkUpdate(hql);
		}
	}

	/**
	 * 查询未分配的菜单
	 * 
	 * @param roleId
	 */
	public List loadLeftMenuForRole(String sysmark, Long roleId)
			throws Exception {
		List list = null;
		StringBuffer sbsql = new StringBuffer();
		sbsql
				.append("select m.* from sys_menu m where m.menucode in(select distinct t4.menucode from(")
				.append(" select t1.menucode from sys_menu t1 where t1.menucode not in ")
				.append(" (")
				.append(
						" select t.menucode from sys_role_menu t where t.roleid="
								+ roleId.longValue())
				.append(") and t1.syscode='").append(sysmark).append("' union")
				.append(
						" select t3.menucode from (select * from sys_menu t1 where t1.menucode not in ")
				.append(" (")
				.append(
						" select t.menucode from sys_role_menu t where t.roleid="
								+ roleId.longValue())
				.append(")) t2 join sys_menu t3 on t2.parentcode=t3.menucode where t3.syscode='").append(sysmark).append("') t4)");

		list = getBaseDao().getResultList(sbsql.toString(),
				SysMenu.class, new String[]{"menucode","menuname","menuenname","parentcode","menuurl","menuflag","sequ","enabled","syscode"});
		if (list == null || list.size() < 1)
			return null;
		return list;
	}

	/**
	 * 查询已分配的菜单
	 * 
	 * @param roleId
	 */
	public List loadRightMenuForRole(String sysmark, Long roleId)
			throws Exception {
		List list = new ArrayList();
		StringBuffer sbsql = new StringBuffer();
		sbsql.append("	select * from sys_menu t1 where t1.menucode in ")
		.append("	(")
		.append(
				"	  select t.menucode from sys_role_menu t where t.roleid="
						+ roleId.longValue())
		.append(" ) and t1.syscode='" + sysmark + "' ").append(
						" order by menucode asc,sequ asc");
		/*sbsql.append(" select distinct t.* from ( ");

		sbsql.append(" select t1.* from sys_menu t1 ");
		sbsql.append(" where t1.menucode in(select t.menucode from sys_role_menu t where t.roleid="
				+ roleId.longValue());
		sbsql.append(")  and t1.syscode='" + sysmark + "' ");

		sbsql.append(" union ").append(
				" select t2.* from sys_menu t2 ")
				.append(" where t2.menucode in(select t1.parentcode from sys_menu t1 where t1.menucode in(select t.menucode from sys_role_menu t where t.roleid="
				+ roleId.longValue());
		sbsql.append(")  and t1.syscode='" + sysmark + "' )");

		sbsql.append(" ) t order by t.menucode asc,t.sequ asc ");*/
		System.out.println(sbsql.toString());
		list = getBaseDao().getResultList(sbsql.toString(),
				SysMenu.class, new String[]{"menucode","menuname","menuenname","parentcode","menuurl","menuflag","sequ","enabled","syscode"});
		if (list == null || list.size() < 1)
			return null;
		return list;
	}

	/**
	 * 查询多个角色的菜单ܲ˵�
	 * 
	 * @param roleIds
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List loadMenusForRoles(String sysmark, String roleIds)
			throws Exception {
		List list = new ArrayList();
		StringBuffer sbsql = new StringBuffer();
		sbsql.append(" select distinct t.* from ( ");
		sbsql
				.append(" select distinct t1.* from H_V_MENURIGHTS t,h_sys_menu t1 ");
		sbsql.append(" where t.menucode=t1.menucode and t.roleid in(" + roleIds
				+ ")");
		sbsql.append("   and t1.sysmark='" + sysmark + "' ");

		sbsql.append(" union ").append(
				" select distinct t2.* from H_V_MENURIGHTS t1,h_sys_menu t2 ")
				.append(" where t1.parentmenucode=t2.menucode ");
		sbsql.append("   and t1.roleid in(" + roleIds + ")");
		sbsql.append("   and t1.sysmark='" + sysmark + "'");

		sbsql.append(" ) t order by t.menucode asc,t.sequ asc ");
		list = getBaseDao().getResultList(sbsql.toString(),
				SysMenu.class, new String[]{});
		if (list == null || list.size() < 1)
			return null;
		return list;
	}

	/**
	 * 
	 * @param roleId
	 * @param menuIds
	 * @param isAdd
	 *            true
	 * @throws Exception
	 */
	public void addRightsForRole(String sysmark, Long roleId, String menuIds)
			throws Exception {
		String[] arrMenuId = menuIds.split(",");
		String ids = markMenucodes(menuIds);
		for (int i = 0; i < arrMenuId.length; i++) {
			if (super.isNullOrEmpty(arrMenuId[i]))
				continue;

			SysRoleMenu mr = new SysRoleMenu();
			mr.setRoleid(Long.valueOf(roleId));
			mr.setMenucode(arrMenuId[i]);
			getBaseDao().save(mr);
		}
	}

	/**
	 * 移除权限操作
	 * 
	 * @throws Exception
	 */
	public void delRightsForRole(String sysmark, Long roleId, String menuIds)
			throws Exception {
		String ids = markMenucodes(menuIds);
		String hql = " delete from SysRoleMenu mr where mr.menucode in(" + ids
				+ ") and mr.roleid=" + roleId.longValue();
		getBaseDao().bulkUpdate(hql);
	}

	private String markMenucodes(String menuIds) {
		String[] arrMenuId = menuIds.split(",");
		String ids = "";
		for (int i = 0; i < arrMenuId.length; i++) {
			if (super.isNullOrEmpty(arrMenuId[i]))
				continue;
			ids += "'" + arrMenuId[i] + "',";
		}
		ids = ids.substring(0, ids.length() - 1);
		return ids;
	}
}
