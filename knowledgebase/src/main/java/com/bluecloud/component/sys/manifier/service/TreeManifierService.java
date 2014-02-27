package com.bluecloud.component.sys.manifier.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bluecloud.component.sys.entity.po.SysOrg;
import com.bluecloud.framework.core.mvc.base.service.impl.BaseServiceImpl;

@Service
public class TreeManifierService extends BaseServiceImpl {
	
	
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
	
	public List<SysOrg> getSysOrgList(SysOrg sysOrg) throws Exception{
		StringBuffer sbhql = new StringBuffer().append(" from SysOrg o where o.enabled='1' and o.parentcode is null ");
		if(sysOrg!=null){
			if(!isNullOrEmpty(sysOrg.getOrgcode())){
				sbhql.append(" and o.orgcode='"+sysOrg.getOrgcode()).append("'");
			}
		}
		return getBaseDao().find(sbhql.toString());
	}
	
	public List<SysOrg> getSysOrgTreeListByPcode(SysOrg sysOrg) throws Exception{
		StringBuffer sbhql = new StringBuffer().append(" from SysOrg o where o.enabled='1' ");
		if(sysOrg!=null){
			if(!isNullOrEmpty(sysOrg.getOrgcode())){
				sbhql.append("and o.parentcode='"+sysOrg.getOrgcode()).append("'");
			}
		}
		return getBaseDao().find(sbhql.toString());
	}
	
	public SysOrg getSysOrg(SysOrg sysOrg) throws Exception{
		String hql=" from SysOrg o where o.enabled='1' ";
		if(sysOrg!=null){
			if(sysOrg.getId()!=null&&!"".equals(sysOrg.getId())){
				hql += " and o.id="+sysOrg.getId();
			}
			if(sysOrg.getOrgcode()!=null&&!"".equals(sysOrg.getOrgcode())){
				hql += " and o.orgcode='"+sysOrg.getOrgcode()+"'";
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
