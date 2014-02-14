package com.bluecloud.framework.core.security.bean;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * <p>
 * 用户扩展实现类
 * </p>
 * 
 * @author dafei
 * 
 */
public class UserDetailsEX implements UserDetails, java.io.Serializable {
	private static final long serialVersionUID = 1L;

	Collection<GrantedAuthority> authorities;
	String username;
	String password;
	boolean isAccountNonExpired = true;
	boolean isAccountNonLocked = true;
	boolean isCredentialsNonExpired = true;
	boolean isEnabled = true;

	// 方便扩展用户对象，留给业务系统自定义用户对象使用
	Object userObj = null;

	public UserDetailsEX() {
	}

	public UserDetailsEX(String username, String password,
			boolean isAccountNonExpired, boolean isAccountNonLocked,
			boolean isCredentialsNonExpired, boolean isEnabled,
			Collection<GrantedAuthority> authorities, Object userObj) {
		this.username = username;
		this.password = password;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonExpired;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.authorities = authorities;
		this.userObj = userObj;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Object getUserObj() {
		return userObj;
	}

	public void setUserObj(Object userObj) {
		this.userObj = userObj;
	}

	// 用户所具有的角色集合
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String[] getRolesForLoginUser() {
		String[] roles = null;
		if (this.getAuthorities() == null || this.getAuthorities().size() < 1)
			return null;
		roles = new String[this.getAuthorities().size()];
		Iterator its = this.getAuthorities().iterator();
		int i = 0;
		while (its.hasNext()) {
			GrantedAuthorityImpl ga = (GrantedAuthorityImpl) its.next();
			roles[i] = ga.getAuthority();
			i++;
		}
		return roles;
	}

}
