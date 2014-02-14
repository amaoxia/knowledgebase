package com.bluecloud.component.sys.modules.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bluecloud.component.sys.entity.po.SysOrg;
import com.bluecloud.framework.core.mvc.base.BaseService;
import com.bluecloud.framework.core.mvc.pager.PaginationSupport;

@Service
public class OrgService extends BaseService {
	
	/**
	 * 新增一个用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String insertSysOrg(SysOrg sysOrg) throws Exception {
		String msg = "";
		try {
			insertObj(sysOrg);
		} catch (Exception e) {
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
	public String updateSysOrg(SysOrg sysOrg) throws Exception {
		String msg = "";
		try {
			getBaseDao().update(sysOrg);
			//updateObj(sysRole.getRoleid()+"", sysRole, new String[]{});
		} catch (Exception e) {
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
	public String delSysOrg(SysOrg sysOrg) throws Exception {
		String msg = "";
		try {
			deleteObj(sysOrg.getOrgid()+"");
		} catch (Exception e) {
			throw e;
		}
		return msg;
	}
	
	public String delSysOrgs(String scodes) throws Exception {
		String msg = "";
		String[] scodeArray = scodes.split(",");
		for(String scode: scodeArray){
			delSysOrgByCode(scode);
		}
		return msg;
	}
	
	public String delSysOrgByCode(String code) throws Exception {
		String msg = "";
		delSysOrgRecursive(code);
		delSysOrgsByCode("'"+code+"',");
		return msg;
	}
	
	public String delSysOrgRecursive(String code) throws Exception {
		String msg = "";
		List<SysOrg> sysOrgList = getSysOrgTreeListByPcode(code);
		
		if(sysOrgList!=null&&sysOrgList.size()>0){
			for(SysOrg sysOrg : sysOrgList){
				delSysOrgByCode(sysOrg.getScode());
			}
		
			String strIds = "";
			for(SysOrg sysOrg : sysOrgList){
				strIds += "'"+sysOrg.getScode()+"',";
			}
			
			delSysOrgsByCode(strIds);
		}
		return msg;
	}
	
	public String delSysOrgsByCode(String scodes) throws Exception {
		String msg = "";
		
		try {
			getBaseDao().bulkUpdate("delete SysOrg o where o.scode in("+scodes.substring(0,scodes.length()-1)+")");
		} catch (Exception e) {
			throw e;
		}
		
		return msg;
	}
	
	public List<SysOrg> getSysOrgTreeList(SysOrg sysOrg) throws Exception{
		List<SysOrg> sysOrgList = new ArrayList<SysOrg>();
		getSysOrgTreeList(sysOrg, sysOrgList);
		return sysOrgList;
	}
	
	public void getSysOrgTreeList(SysOrg sysOrg, List<SysOrg> sysOrgList) throws Exception{
		List<SysOrg> orgList = getSysOrgTreeListByPcode(sysOrg);
		if(orgList!=null&&orgList.size()>0){
			sysOrgList.addAll(orgList);
			for(SysOrg org : orgList){
				getSysOrgTreeList(org, sysOrgList);
			}
		}
	}
	
	public PaginationSupport getSysOrgList(SysOrg sysOrg, PaginationSupport pager) throws Exception{
		StringBuffer sbhql = new StringBuffer().append(" from SysOrg o where o.enabled='1' and o.parentcode is null ");
		if(sysOrg!=null){
			if(!isNullOrEmpty(sysOrg.getOrgcode())){
				sbhql.append(" and o.orgcode='"+sysOrg.getOrgcode()).append("'");
			}
			if(!isNullOrEmpty(sysOrg.getOrgasname())){
				sbhql.append(" and o.orgasname like '%"+sysOrg.getOrgasname()).append("%' ");
			}
		}
		return getBaseDao().loadPageResultByHQuery(sbhql.toString(), pager.getPageSize(), pager.getStartIndex(), null);
	}
	
	public List<SysOrg> getSysOrgTreeListByPcode(SysOrg sysOrg) throws Exception{
		StringBuffer sbhql = new StringBuffer().append(" from SysOrg o where o.enabled='1' ");
		if(sysOrg!=null){
			if(!isNullOrEmpty(sysOrg.getScode())){
				sbhql.append("and o.parentcode='"+sysOrg.getScode()).append("'");
			}
		}
		return getBaseDao().find(sbhql.toString());
	}
	
	public List<SysOrg> getSysOrgTreeListByPcode(String parentcode) throws Exception{
		StringBuffer sbhql = new StringBuffer().append(" from SysOrg o where o.enabled='1' ");
		if(!isNullOrEmpty(parentcode)){
			sbhql.append("and o.parentcode='"+parentcode).append("'");
		}
		return getBaseDao().find(sbhql.toString());
	}
	
	public SysOrg getSysOrg(SysOrg sysOrg) throws Exception{
		String hql=" from SysOrg o where o.enabled='1' ";
		if(sysOrg!=null){
			if(sysOrg.getOrgid()!=null&&!"".equals(sysOrg.getOrgid())){
				hql += " and o.orgid="+sysOrg.getOrgid();
			}
			if(sysOrg.getOrgcode()!=null&&!"".equals(sysOrg.getOrgcode())){
				hql += " and o.orgcode='"+sysOrg.getOrgcode()+"'";
			}
			if(sysOrg.getScode()!=null&&!"".equals(sysOrg.getScode())){
				hql += " and o.scode='"+sysOrg.getScode()+"'";
			}
		}
		List<SysOrg> sysOrgList = getBaseDao().find(hql);
		if(sysOrgList==null||sysOrgList.size()<=0)return sysOrg;
		return sysOrgList.get(0);
	}
	
	public SysOrg getSysOrgByPrimkey(String orgid) throws Exception{
		return (SysOrg)queryObjByPK(orgid);
	}
}
