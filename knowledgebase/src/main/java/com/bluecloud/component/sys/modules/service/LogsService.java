package com.bluecloud.component.sys.modules.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bluecloud.component.sys.entity.po.SysLog;
import com.bluecloud.framework.core.mvc.base.service.BaseService;

@Service
public class LogsService extends BaseService {

	public List<SysLog> getLogsList(SysLog sysLog) throws Exception{
		String hql=" from SysLog s where 1=1 ";
		if(sysLog!=null) {
			if(!super.isNullOrEmpty(sysLog.getLoginuser())) {
				hql += " and s.loginuser like '%"+StringUtils.replace(sysLog.getLoginuser().trim(), "'", "''")+"%' ";
			}
		}
		return getBaseDao().find(hql);
	}
}
