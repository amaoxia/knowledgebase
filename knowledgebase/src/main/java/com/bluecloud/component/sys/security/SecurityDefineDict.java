package com.bluecloud.component.sys.security;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 *<p>安全定义字典类</p>
 * @author dafei
 *
 */
public final class SecurityDefineDict {
	private SecurityDefineDict(){}
	
	//用户、角色、资源对象定义的key,此定义的key必须对应于业务实体对象用户、角色、资源中的属性名称
	public static final String USER_OBJ_PROP_ACCOUNT="loginuser";
	public static final String USER_OBJ_PROP_PASSWORD="loginpwd";
	public static final String ROLE_OBJ_PROP_NAME="rolename";
	public static final String RESOURCE_OBJ_PROP_CODE="menucode";
	public static final String RESOURCE_OBJ_PROP_URL="menuurl";
	public static final String RESOURCE_OBJ_SUPER_ADMINISTRATOR="administrator";
	
	public static Object getObjValue(Object obj,String key) throws Exception
	{
		return BeanUtils.getProperty(obj, key);
	}
}
