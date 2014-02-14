package com.bluecloud.component.sys.modules.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bluecloud.component.sys.entity.po.SysOrg;
import com.bluecloud.component.sys.entity.po.SysParam;
import com.bluecloud.component.sys.entity.po.SysUser;
import com.bluecloud.component.sys.entity.vo.SysUserVO;
import com.bluecloud.framework.core.mvc.base.BaseService;
@Service
public class ParamService extends BaseService {	
	
	public List<SysParam> loadParamList(SysParam sysParam) throws Exception {
		StringBuffer hql = new StringBuffer().append(" from SysParam s where 1=1");
		if(sysParam!=null) {
			if(!super.isNullOrEmpty(sysParam.getNotes())) {
				hql.append(" and s.notes like '%"
							+ StringUtils.replace(sysParam.getNotes().trim(), "'",
									"''") + "%' ");
			}
		}
		hql.append(" order by s.id desc ");
		return super.getBaseDao().find(hql.toString());
	}
	/**
	 * 修改系统参数信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String updateParam(SysParam param) throws Exception {
		String msg="";
		try {
			getBaseDao().update(param);
		} catch(Exception e) {
			throw e;
		}
		return msg;
	}
	
	
	public SysParam loadSysParam(SysParam sysParam) {
		StringBuffer sbhql = new StringBuffer().append(" from SysParam s  where 1=1 ");
		if (sysParam.getId()!=null) {
			sbhql.append(" and s.id = '"+sysParam.getId()+"'");
		}
		List<SysParam> list = super.getBaseDao().find(sbhql.toString());
		if (list==null||list.size() <= 0)
			return null;
		else
			return list.get(0);
	}
	
	/**
	 * 解析逗号隔开Id串
	* <p>方法名称: toTranslateIds|描述: </p>
	* @param Ids
	* @return
	 */
	public String toTranslateIds(String Ids) {
		String[] IdsArray = Ids.split(",");
		String strIds = "";
		/*if(IdsArray.length==1) {
			return IdsArray[0];
		}*/
		for(String Id : IdsArray) {
			strIds += "'"+Id+"',";
		}
		strIds = strIds.substring(0, strIds.length()-1);
		return strIds;
	}
	public SysOrg loadOrg(SysOrg sysOrg) {
		String sql = " from SysOrg s  where 1=1 ";
		if(sysOrg!=null) {
			if(!super.isNullOrEmpty(sysOrg.getOrgcode())) {
				sql += " and s.orgcode = "+sysOrg.getOrgcode()+"";
			}
			if(sysOrg.getOrgid()!=null) {
				sql += " and s.orgid = "+sysOrg.getOrgid()+"";
			}
		}
		List<SysOrg> list = super.getBaseDao().find(sql.toString());
		if (list==null||list.size() <= 0)
			return null;
		else
			return list.get(0);
	}
}
