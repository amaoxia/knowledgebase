package com.bluecloud.framework.core.security.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;

import com.bluecloud.component.sys.entity.po.SysMenu;
import com.bluecloud.component.sys.entity.po.SysRole;
import com.bluecloud.component.sys.security.SecurityDefineDict;
import com.bluecloud.component.sys.security.service.SecurityService;

/**
 * 
 * @author HuJun
 *
 */
public class CustomSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private String webRealPath = "";
	private SecurityService securityService;

	private AntPathRequestMatcher pathMatcher;
	/**<url,Collection<role>>**/
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	Logger logger = Logger.getLogger(CustomSecurityMetadataSource.class);
	
	public CustomSecurityMetadataSource(SecurityService securityService) {
		this.securityService = securityService;
		//加载角色资源集合
		loadResourceDefine();
	}

	/**
	 * 加载所有资源信息
	 */
	private void loadResourceDefine() {
		try {
			if (resourceMap == null) {
				resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
				
				logger.info("加载所有权限集合");
				
				List<SysMenu> resources = this.securityService.getResourceList();
				for (int i = 0; i < resources.size(); i++) {
					String rescode = (String) SecurityDefineDict.getObjValue(
							resources.get(i),
							SecurityDefineDict.RESOURCE_OBJ_PROP_CODE);
					String resurl = (String) SecurityDefineDict.getObjValue(
							resources.get(i),
							SecurityDefineDict.RESOURCE_OBJ_PROP_URL);
					if (resurl == null || "".equals(resurl.trim()))
						continue;
					resourceMap.put(resurl, listToCollection(rescode));
				}
				
				logger.info("权限加载完毕");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据用户请求的url,查询对应的角色信息
	 */
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		Collection<ConfigAttribute> returnCollection = null;

		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		
		logger.info("requestUrl is:" + request.getRequestURI());
		
		try {
			if (resourceMap == null) {
				loadResourceDefine();
			}

			for(Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()){
				String resurl = (String) entry.getKey();
				pathMatcher = new AntPathRequestMatcher(resurl);
				if (pathMatcher.matches(request)) {
					returnCollection = resourceMap.get(resurl);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnCollection;
	}

	/**
	 * 将url对应角色信息组装成集合
	 * @param rescode
	 * @return
	 * @throws Exception
	 */
	private Collection<ConfigAttribute> listToCollection(String rescode)
			throws Exception {
		List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
		//给超级管理员，赋值所有角色菜单权限
		list.add(new SecurityConfig(SecurityDefineDict.RESOURCE_OBJ_SUPER_ADMINISTRATOR));
		try {
			//查询url对应的角色集合
			List roles = this.securityService.getRoleListByMenucode(rescode);
			for (int i = 0; i < roles.size(); i++) {
				String rolename = (String) SecurityDefineDict.getObjValue(roles
						.get(i), SecurityDefineDict.ROLE_OBJ_PROP_NAME);
				list.add(new SecurityConfig(rolename));
			}
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public String getWebRealPath() {
		return webRealPath;
	}

	public void setWebRealPath(String webRealPath) {
		this.webRealPath = webRealPath;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
}
