package com.bluecloud.framework.core.security.manager;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限策略管理器
 * @author HuJun
 *
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {
	
	Logger logger = Logger.getLogger(CustomAccessDecisionManager.class);
	
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null)
			return;
		
		for(ConfigAttribute configAttribute : configAttributes){
			String rolename = ((SecurityConfig) configAttribute).getAttribute();
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if (rolename.trim().equals(grantedAuthority.getAuthority().trim())) {
					return;
				}
			}
		}
		throw new AccessDeniedException(" 没有权限访问！ ");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}
