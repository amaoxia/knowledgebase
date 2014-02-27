package com.bluecloud.component.sys.modules.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluecloud.component.sys.entity.po.SysDict;
import com.bluecloud.component.sys.modules.service.DictService;
import com.bluecloud.framework.core.mvc.base.controller.BaseController;
import com.bluecloud.framework.core.mvc.base.service.IBaseService;
import com.bluecloud.framework.utils.DateUtil;
import com.bluecloud.framework.utils.FilePathUtil;
import com.bluecloud.framework.utils.GenerateCode;

@Controller
public class DictController extends BaseController {
	@Resource(name = "dictService")
	DictService dictService;
	/**
	 * 数据字典编辑列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/dict" )
	public String doDictList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = "system/dictList";
		SysDict sysDict = new SysDict();
		sysDict.setDictkey(request.getParameter("dict.dictkey"));
		try {
			List<SysDict> dictList = dictService.loadDictList(sysDict);
			request.setAttribute("dictList", dictList);
		} catch(Exception e){
			e.printStackTrace();
		}
		return forward;		
	}
	/**
	 * 跳转到添加或编辑页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/dictToEdit" )
	public String toEditDict(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = "system/dictAdd";
		String Ids = request.getParameter("Ids");
		if(!super.isEmptyOrNull(Ids)) {
			forward = "system/dictEdit";
			SysDict sysDict = new SysDict();
			sysDict.setId(Long.valueOf(Ids));
			SysDict entity = dictService.loadSysDict(sysDict,null);
			request.setAttribute("sysDict", entity);
		}
		return forward;	
	}
	/**
	 * 添加字典大类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/dictAdd", method = RequestMethod.POST)
	public String doAddDict(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String responseString = "true";
		SysDict sysDict = new SysDict();
		sysDict.setDictkey(request.getParameter("dictkey"));
		sysDict.setDictcode("D"+GenerateCode.getProductCode());
		sysDict.setDictname(request.getParameter("dictname"));
		sysDict.setDescinfo(request.getParameter("descinfo"));
		sysDict.setParentcode("0");
		sysDict.setEnabled(Long.valueOf(1));
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currTime = formatter.format(date);
		//sysUser.setCreater((String)getSession(request, "j_user"));
		sysDict.setCreatetime(currTime);
		try{
			dictService.insertDict(sysDict);
		}catch(Exception e) {
			responseString = "false";
			e.printStackTrace();
		}finally {
			response.getWriter().print(responseString);
		}
		return null;	
	}
	
	/**
	 * 修改字典大类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/dictEdit", method = RequestMethod.POST)
	public String doEditDict(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String responseString = "true";
		SysDict sysDict = new SysDict();
		sysDict.setId(Long.valueOf(request.getParameter("dictid")));
		sysDict.setDictkey(request.getParameter("dictkey"));
		sysDict.setDictcode(request.getParameter("dictcode"));
		sysDict.setDictname(request.getParameter("dictname"));
		sysDict.setDescinfo(request.getParameter("descinfo"));
		sysDict.setEnabled(Long.valueOf(request.getParameter("enabled")));
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currTime = formatter.format(date);
		sysDict.setEdittime(currTime);
		try{
			dictService.updateDict(sysDict);
		}catch(Exception e) {
			responseString = "false";
			e.printStackTrace();
		}finally {
			response.getWriter().print(responseString);
		}
		return null;	
	}
	/**
	 * 查询字典大类下的明细列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/dictToDetail", method = RequestMethod.POST)
	public String dictToDetail(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = "system/dictDetailList";
		String dictpcode = request.getParameter("dictpcode");
		String dictpkey = request.getParameter("dictpkey");
		String dictpname = java.net.URLDecoder.decode(request.getParameter("dictpname"),"UTF-8");
		SysDict sysDict = new SysDict();
		sysDict.setParentcode(dictpcode);
		try {
			List<SysDict> dictDetailList = dictService.loadDictDetailList(sysDict);
			request.setAttribute("dictList", dictDetailList);
			request.setAttribute("dictpkey", dictpkey);
			request.setAttribute("dictpname", dictpname);
			request.setAttribute("dictpcode", dictpcode);
		} catch(Exception e){
			e.printStackTrace();
		}
		return forward;
	}
	/**
	 * 维护字典明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/dictAddItems")
	public String dictAddItems(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String forward = "system/dictDetailList";
		String dictpcode = request.getParameter("dictpcode");
		String dictpkey = request.getParameter("dictpkey");
		String dictpname = java.net.URLDecoder.decode(request.getParameter("dictpname"),"UTF-8");
		String dictkey = request.getParameter("dictkey");
		String dictname = java.net.URLDecoder.decode(request.getParameter("dictname"),"UTF-8");
		String dictid = request.getParameter("dictid");
		SysDict sysDict = new SysDict();
		if(!super.isEmptyOrNull(dictid)) {
			try {
				SysDict entity = new SysDict();
				sysDict.setId(Long.valueOf(dictid));
				entity = dictService.loadSysDict(sysDict,null);
				entity.setParentcode(dictpcode);
				entity.setDictkey(dictkey);
				entity.setDictname(dictname);
				dictService.updateDict(entity);
			} catch(Exception e){
				e.printStackTrace();
			}
		} else {
			sysDict.setParentcode(dictpcode);
			sysDict.setDictkey(dictkey);
			sysDict.setDictname(dictname);
			sysDict.setEnabled(Long.valueOf(1));
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currTime = formatter.format(date);
			//sysUser.setCreater((String)getSession(request, "j_user"));
			sysDict.setCreatetime(currTime);
			sysDict.setDictcode("D"+GenerateCode.getProductCode());
			try {
				dictService.insertDict(sysDict);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		try {
			SysDict dict = new SysDict();
			dict.setParentcode(dictpcode);
			List<SysDict> dictDetailList = dictService.loadDictDetailList(dict);
			request.setAttribute("dictList", dictDetailList);
			request.setAttribute("dictpkey", dictpkey);
			request.setAttribute("dictpname", dictpname);
			request.setAttribute("dictpcode", dictpcode);
		} catch(Exception e){
			e.printStackTrace();
		}
		return forward;
	}
	/**
	 * 删除字典大类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/dictDelete", method = RequestMethod.POST)
	public String doDelDict(HttpServletRequest request,HttpServletResponse response) throws Exception {
		dictService.delDict(request.getParameter("Ids"));
		return doDictList(request,response);	
	}
	/**
	 * 删除字典明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/dictDeleteItems")
	public String dictDeleteItems(HttpServletRequest request,HttpServletResponse response) throws Exception {
		dictService.delDictItems(request.getParameter("Ids"));
		return dictToDetail(request,response);	
	}
	/**
	 * 检查是否有重复的字典大类编号
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/dictCheck", method = RequestMethod.POST)
	public String checkRepeat(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String responseString = "true";
		try{
			SysDict sysDict = new SysDict();
			sysDict.setDictkey((request.getParameter("dictkey")));
			SysDict entity = dictService.loadSysDict(sysDict,"0");
			if(entity != null) responseString = "false";
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
	@RequestMapping(value = "/sys/dictBack", method = RequestMethod.POST)
	public String toGoBack(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return doDictList(request,response);
	}
	@Override
	protected IBaseService getEntityService() {
		return dictService;
	}
	
}
