package com.bluecloud.framework.servlet;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.SqlSessionFactory;

import com.bluecloud.component.cache.service.DictDataCacheService;
import com.bluecloud.framework.AppConstant;
import com.bluecloud.framework.AppUtils;
import com.bluecloud.framework.Entry;
import com.bluecloud.framework.core.mvc.base.SessionFactory;
import com.bluecloud.framework.core.mvc.sqlmanager.SQLManager;

/**
 * 
 *<p>应用服务启动类，需要在web.xml中配置</p>
 *<p>主要在应用启动时加载一些初始化的参数及系统配置等信息</p>
 * @author hujun at 2011-11-8
 *
 */
public class StartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		try {
			//加载配置参数
			loadConfigInfo();
			SessionFactory.getInstance();

			//加载应用中所有含有sql脚本的xml文件
			//SQLManager.initSqlMap(AppConstant.getConfig("classpath").toString());

			//设置系统代码,包括子系统代码
			setSystemCodes(super.getInitParameter(AppConstant.SYSTEM_MARK));

			//加载数据字典数据
			loadDictData();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载所有配置信息
	 * @throws Exception
	 */
	private void loadConfigInfo() throws Exception {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("config.sysconfig");
			Enumeration<String> enum1 = rb.getKeys();
			while (enum1.hasMoreElements()) {
				String skey = enum1.nextElement();
				String svalue = rb.getString(skey);
				AppConstant.putConfig(skey, svalue);
			}
			//设置web应用路径
			AppConstant.putConfig(AppConstant.APP_PATH_KEY, super
					.getServletConfig().getServletContext().getRealPath("/"));
			AppConstant.putConfig("classpath", AppConstant
					.getConfig(AppConstant.APP_PATH_KEY)
					+ "WEB-INF/classes");
		} catch (Exception e) {
			System.out.println("加载系统配置文件失败！");
			e.printStackTrace();
		}
	}

	//加载系统代码,包括子系统代码
	private void setSystemCodes(String s) {
		if (s == null || "".equals(s) || s.indexOf(";") == -1)
			return;
		String[] codes = s.split(";");
		if (codes.length < 1)
			return;
		List list = new ArrayList();
		for (int i = 0; i < codes.length; i++) {
			Entry entry = new Entry();
			String[] arrValue = codes[i].split(",");
			entry.setKey(arrValue[0].replaceAll("\n", "").replaceAll("\t", "")
					.replaceAll(" ", ""));
			entry.setValue(arrValue[1]);
			list.add(entry);
			if (i == 0) {
				AppConstant.SYSTEM_MARK_DEFAULT = arrValue[0].replaceAll("\n",
						"").replaceAll("\t", "").replaceAll(" ", "");
			}
		}
		AppConstant.ConfigMap.put(AppConstant.SYSTEM_MARK, list);
	}

	private void loadDictData() throws Exception {
		try {
			DictDataCacheService dictCache = (DictDataCacheService) AppUtils
					.getBean("dictDataCacheService");
			if (dictCache == null) {
				throw new Exception("加载字典数据失败！");
			}
			dictCache.loadDictData();
		} catch (Exception e) {
			System.out.println("---加载字典数据失败！" + e.getMessage());
			e.printStackTrace();
		}
	}
}
