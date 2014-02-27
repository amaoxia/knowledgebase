package com.bluecloud.knowledgebase.modules.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bluecloud.component.sys.entity.po.SysOrg;
import com.bluecloud.component.sys.entity.po.SysUser;
import com.bluecloud.component.sys.entity.vo.SysUserVO;
import com.bluecloud.framework.core.mvc.base.dao.SessionFactory;
import com.bluecloud.framework.core.mvc.base.service.impl.BaseServiceImpl;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;
import com.bluecloud.framework.core.tuple.IbatisSql;

public class UserService extends BaseServiceImpl {
	/**
	 * 查询用户信息
	 * @param sysUser
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public PaginationSupport loadUserList(SysUser sysUser,PaginationSupport pager) throws Exception {
		SysUserVO sysUserVO = new SysUserVO();
		sysUserVO.setOrgname("软通");
		sysUserVO.setSortcolumns("username");
		IbatisSql ibatisSql = SessionFactory.getIbatisSql("UserInfo.findPage", sysUserVO);
		
		String hql=" select new com.component.sys.entity.vo.SysUserVO(u.id,u.loginuser,u.usercode,u.username,u.usersex,u.usertel,u.createtime,u.edittime,o.orgname) ";
		hql+=" from SysUser u,SysOrg o where u.orgid=o.orgid ";
		if(sysUser!=null){
			if(sysUser.getId()!=null) {
				hql+=" and u.id = "+ sysUser.getId();
			}
			if(!super.isNullOrEmpty(sysUser.getUsername())) {
				hql+=" and u.username like '%"+StringUtils.replace(sysUser.getUsername().trim(), "'", "''")+"%' ";
			}
			if(!super.isNullOrEmpty(sysUser.getLoginuser())) {
				hql+=" and u.loginuser like '%"+StringUtils.replace(sysUser.getLoginuser().trim(), "'", "''")+"%' ";
			}
			hql+=" order by u.createtime desc ";
		}
		return getBaseDao().loadPageResultByHQuery(hql, pager.getPageSize(), pager.getStartIndex(), null);
	}
	/**
	 * 新增一个用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String insertUser(SysUser user) throws Exception {
		String msg="";
		try {
			getBaseDao().save(user);
		} catch(Exception e) {
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
	public String updateUser(SysUser user) throws Exception {
		String msg="";
		try {
			getBaseDao().update(user);
		} catch(Exception e) {
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
	public String delUser(String Ids) throws Exception {
		String msg="";
		try {
			Ids = toTranslateIds(Ids);
			String hql = " delete from SysUser s where s.id in(" + Ids + ")";
			getBaseDao().bulkUpdate(hql);
		} catch(Exception e) {
			throw e;
		}
		return msg;
	}
	
	public List<SysOrg> getSysOrgTreeList() throws Exception{
		StringBuffer sbhql = new StringBuffer().append(" from SysOrg o where o.enabled='1' ");
		return getBaseDao().find(sbhql.toString());
	}
	
	public SysUser loadSysUser(SysUser sysUser) {
		StringBuffer sbhql = new StringBuffer().append(" from SysUser s  where 1=1 ");
		if (!super.isNullOrEmpty(sysUser.getLoginuser())) {
			sbhql.append(" and s.loginuser = '"+StringUtils.replace(sysUser.getLoginuser().trim(),"'","''")+"'");
		}
		if (sysUser.getId()!=null) {
			sbhql.append(" and s.id = '"+sysUser.getId()+"'");
		}
		List<SysUser> list = super.getBaseDao().find(sbhql.toString());
		if (list==null||list.size() <= 0)
			return null;
		else
			return list.get(0);
	}
	
	public SysOrg loadOrg(SysOrg sysOrg) {
		String sql = " from SysOrg s  where 1=1 ";
		if(sysOrg!=null) {
			if(!super.isNullOrEmpty(sysOrg.getOrgcode())) {
				sql += " and s.orgcode = '"+sysOrg.getOrgcode()+"'";
			}
			if(!super.isNullOrEmpty(sysOrg.getScode())) {
				sql += " and s.scode = '"+sysOrg.getScode()+"'";
			}
			if(sysOrg.getId()!=null) {
				sql += " and s.id = "+sysOrg.getId()+"";
			}
		}
		List<SysOrg> list = super.getBaseDao().find(sql.toString());
		if (list==null||list.size() <= 0)
			return null;
		else
			return list.get(0);
	}
}
