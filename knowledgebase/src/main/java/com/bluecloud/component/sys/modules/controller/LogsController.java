package com.bluecloud.component.sys.modules.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluecloud.component.sys.entity.po.SysLog;
import com.bluecloud.component.sys.modules.service.LogsService;
import com.bluecloud.framework.core.mvc.base.controller.BaseController;
import com.bluecloud.framework.core.mvc.base.service.IBaseService;

@Controller
public class LogsController extends BaseController {
	@Resource(name = "logsService")
	LogsService logsService;

	@RequestMapping(value = "/sys/logs")
	public String doLogsList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = "system/loggerList";
		SysLog sysLog = new SysLog();
		sysLog.setLoginuser(request.getParameter("logEntity.loginuser"));
		List<SysLog> logsList = logsService.getLogsList(sysLog);
		request.setAttribute("logsList", logsList);
		return forward;
	}

	@Override
	protected IBaseService getEntityService() {
		return logsService;
	}

}
