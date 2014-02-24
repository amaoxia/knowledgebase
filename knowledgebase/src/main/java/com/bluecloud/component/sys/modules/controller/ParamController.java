package com.bluecloud.component.sys.modules.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluecloud.component.sys.entity.po.SysParam;
import com.bluecloud.component.sys.modules.service.ParamService;
import com.bluecloud.framework.AppConstant;
import com.bluecloud.framework.core.mvc.base.controller.BaseController;

@Controller
public class ParamController extends BaseController {
	private static final String PARAM_LIST = "system/paramList";
	private static final String PARAM_EDIT = "system/paramEdit";
	@Resource(name = "paramService")
	ParamService paramService;
	
	/**
	 * 系统参数信息查询列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/param" )
	public String doParamList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = PARAM_LIST;
		SysParam sysParam = new SysParam();
		sysParam.setNotes(request.getParameter("notes"));
		try {
			List<SysParam> paramList = paramService.loadParamList(sysParam);
			request.setAttribute("paramList", paramList);
		} catch(Exception e){
			e.printStackTrace();
		}
		return forward;		
	}
	/**
	 * 跳转到修改页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/paramToEdit" )
	public String toEditParam(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = PARAM_EDIT;
		String Ids = request.getParameter("Ids");
		if(!super.isEmptyOrNull(Ids)) {
			SysParam entity = new SysParam();
			entity.setId(Long.valueOf(Ids));
			SysParam sysParam = paramService.loadSysParam(entity);
			request.setAttribute("sysParam", sysParam);
		}
		return forward;	
	}
	/**
	 * 修改用户信息
	 */
	@RequestMapping(value = "/sys/paramEdit", method = RequestMethod.POST)
	public String doEditParam(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String responseString = "true";
		SysParam entity = new SysParam();
		entity.setId(Long.valueOf(request.getParameter("sysParamid")));
		SysParam sysParam = paramService.loadSysParam(entity);
		sysParam = this.setParamEntity(sysParam, request);
		try{
			paramService.updateParam(sysParam);
			AppConstant.ConfigMap.remove(sysParam.getParamkey());//刷新缓存
			AppConstant.ConfigMap.put(sysParam.getParamkey(),sysParam.getParamvalue());
		}catch(Exception e) {
			responseString = "false";
			e.printStackTrace();
		}finally {
			response.getWriter().print(responseString);
		}
		return null;	
	}
	/**
	 * 返回
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/paramBack", method = RequestMethod.POST)
	public String toGoBack(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return doParamList(request,response);
	}
	/**
	 * 取得页面参数存入实体
	 * @param sysParam
	 * @param request
	 * @return
	 */
	private SysParam setParamEntity(SysParam sysParam, HttpServletRequest request) {
		sysParam.setParamkey(request.getParameter("paramkey"));
		sysParam.setParamvalue(request.getParameter("paramvalue"));
		sysParam.setEnabled(Long.valueOf("1"));//是否有效1：有效，0：无效
		//sysUser.setEditer((String)getSession(request, "j_user"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currTime = formatter.format(date);
		sysParam.setEdittime(currTime);
		return sysParam;
	}
	
}
