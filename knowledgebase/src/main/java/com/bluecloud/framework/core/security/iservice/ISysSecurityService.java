package com.bluecloud.framework.core.security.iservice;

import java.util.List;

/**
 * 
 *<p>系统安全服务接口</p>
 *<p>主要功能提供是将系统中权限提供于spring security3.0的安全管理器</p>
 * @author dafei
 *
 */
public interface ISysSecurityService {
	/**
	 * 获取用户详细信息
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public Object getUserByAccount(String account) throws Exception;

	/**
	 * 获取用户拥有的角色集合
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	public List getRoleListByAccount(String account) throws Exception;

	/**
	 * 获取角色下所有功能菜单权限
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	public List getResourceListByRole(String rolename) throws Exception;

	/**
	 * 获取系统中所有菜单权限
	 */
	public List getResourceList() throws Exception;

	/**
	 * 获得所有角色集合
	 * @return
	 * @throws Exception
	 */
	public List getRoleListByMenucode(String menucode) throws Exception;
}
