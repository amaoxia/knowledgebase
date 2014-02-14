package com.bluecloud.component.sys.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bluecloud.component.sys.entity.po.SysMenu;
import com.bluecloud.framework.core.mvc.base.BaseService;
import com.bluecloud.framework.core.mvc.base.IbatisSql;
import com.bluecloud.framework.core.mvc.base.SessionFactory;

/**
 * 系统安全服务实现类
 * @author HuJun
 *
 */
@Service("securityService")
public class SecurityService extends BaseService {//implements ISysSecurityService
	/**
	 * 获取用户信息
	 */
	public Object getUserByAccount(String account) throws Exception {
		String hql = " from SysUser u where u.loginuser='" + account + "'";
		List list = super.getBaseDao().find(hql);
		if (list == null || list.size() < 1)
			return null;
		return list.get(0);
	}

	/**
	 * 获取用户拥有的角色集合
	 */
	public List getRoleListByAccount(String account) throws Exception {
		String hql = " select r from SysRole r,SysUser u,SysUserRole ur where r.roleid=ur.roleid and u.userid=ur.userid and u.loginuser='"
				+ account + "'";
		return super.getBaseDao().find(hql);
	}

	/**
	 * 获取所有菜单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getResourceListByRole(String rolename) throws Exception {
		String hql = " select m from SysRole r,SysMenu m,SysRoleMenu rm where  r.roleid=rm.roleid and m.menucode=rm.menucode ";
		hql += "  and r.rolename='" + rolename + "'";
		return super.getBaseDao().find(hql);
	}

	/**
	 * 获取所有菜单url集合
	 */
	public List getResourceList() throws Exception {
		String hql = " from SysMenu m ";
		return super.getBaseDao().find(hql);
	}

	/**
	 * 获取所有菜单url集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getRoleListByMenucode(String menucode) throws Exception {
		String hql = " select r from SysRole r,SysRoleMenu rm where r.roleid=rm.roleid and rm.menucode='"
				+ menucode + "'";
		return super.getBaseDao().find(hql);
	}

	/**
	 * 获取用户功能权限集合
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public List loadRightsByLoginUser(String loginuser, String syscode)
			throws Exception {
		List menuList = null;
		Map map = new HashMap();
		map.put("loginuser", loginuser);
		map.put("syscode", syscode);
		// 如果登录用户为超级管理员身份，则查询超级管理员权限
		if (loginuser.equals("administrator")) {
			return loadRightsByAdmin(syscode);
		}
		//String sql = getSQL("getRightsByAccount", map);
		IbatisSql ibatisSql = SessionFactory.getIbatisSql("Security.getRightsByAccount", map);
		menuList = super.getBaseDao().getResultList(ibatisSql.getSql(), SysMenu.class, ibatisSql.getParameters());
		return menuList;
	}

	/**
	 * 获取超级管理员所有菜单权限
	 * 
	 * @param syscode
	 *            系统代码
	 * @return
	 * @throws Exception
	 */
	public List loadRightsByAdmin(String syscode) throws Exception {
		List menuList = null;
		String hql = " from SysMenu m where m.enabled=1 and m.syscode='"
				+ syscode + "' order by m.menucode asc ";
		return super.getBaseDao().find(hql);
	}
}
