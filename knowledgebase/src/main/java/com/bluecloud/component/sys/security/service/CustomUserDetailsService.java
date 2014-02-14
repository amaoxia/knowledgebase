package com.bluecloud.component.sys.security.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bluecloud.component.sys.entity.po.SysUser;
import com.bluecloud.component.sys.security.SecurityDefineDict;
import com.bluecloud.framework.AppConstant;
import com.bluecloud.framework.core.security.bean.UserDetailsEX;

/**
 * 用户验证管理类
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	@Resource(name = "securityService")
	private SecurityService securityService;

	/**
	 * 验证用户权限信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails user = null;
		try {
			Object userobj = null;
			//获取业务系统中自定义用户对象
			String superAdminName = AppConstant.getConfig("sys.superAdminName");
			if(username.equals(superAdminName)){
				boolean enableSuperAdmin = Boolean.parseBoolean(AppConstant.getConfig("sys.enableSuperAdmin"));
				if(enableSuperAdmin){
					SysUser admin = new SysUser();
					admin.setLoginuser(username);
					admin.setLoginpwd(AppConstant.getConfig("sys.superAdminPass"));
					userobj = admin;
				}
			}else{
				userobj = securityService.getUserByAccount(username);
			}
			if (userobj == null) {
				throw new UsernameNotFoundException(username);
			}

			//取得登录密码
			String password = (String) SecurityDefineDict.getObjValue(userobj,
					SecurityDefineDict.USER_OBJ_PROP_PASSWORD);

			//组装用户权限信息
			Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(username);

			//组装用户扩展信息
			boolean enables = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			return new UserDetailsEX(username, password, enables,
					accountNonExpired, credentialsNonExpired, accountNonLocked,
					grantedAuths, userobj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 取得用户的角色权限
	 * @param account
	 * @return
	 * @throws Exception
	 */
	private Set<GrantedAuthority> obtionGrantedAuthorities(String account)
			throws Exception {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

		if("administrator".equals(account)){
			authSet.add(new SimpleGrantedAuthority(SecurityDefineDict.RESOURCE_OBJ_SUPER_ADMINISTRATOR));
			return authSet;
		}
		//根据用户获取所拥有角色
		List roles = securityService.getRoleListByAccount(account);
		if (roles == null || roles.size() < 1)
			return authSet;
		for (int i = 0; i < roles.size(); i++) {
			String rolename = (String) SecurityDefineDict.getObjValue(roles
					.get(i), SecurityDefineDict.ROLE_OBJ_PROP_NAME);

			authSet.add(new SimpleGrantedAuthority(rolename));
		}
		return authSet;
	}
}
