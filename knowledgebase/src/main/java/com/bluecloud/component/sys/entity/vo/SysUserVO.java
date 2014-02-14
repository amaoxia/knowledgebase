package com.bluecloud.component.sys.entity.vo;

import com.bluecloud.component.sys.entity.po.SysUser;

/**
 * AbstractSysDict entity provides the base persistence definition of the
 * SysDict entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysUserVO extends SysUser {

	// Fields

	private String orgname;
	
	public SysUserVO()
	{
		super();
	}
	
	public SysUserVO(Long userid,String loginuser,String usercode,String username,Long usersex,String usertel,String createtime,String edittime,String orgname)
	{
		super.setUserid(userid);
		super.setLoginuser(loginuser);
		super.setUsercode(usercode);
		super.setUsername(username);
		super.setUsersex(usersex);
		super.setUsertel(usertel);
		super.setCreatetime(createtime);
		super.setEdittime(edittime);
		this.setOrgname(orgname);
	}
	
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrgname() {
		return orgname;
	}
	
	

}