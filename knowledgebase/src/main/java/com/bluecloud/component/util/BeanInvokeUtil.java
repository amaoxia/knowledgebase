package com.bluecloud.component.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.bluecloud.framework.utils.MiscUtil;

/**
 * 
 * <p>
 * JavaBean表单属性专用转换类
 * </p>
 * 
 * @author dafei
 * 
 */
public final class BeanInvokeUtil {
	private BeanInvokeUtil() {
	}

	/**
	 * 将request中的数据封闭在java bean中
	 * 
	 * @param beanPrifex
	 *            Web页面中引用的bean简写，如,user.name,这里指的是user
	 * @param beanClass
	 *            指javabean 所对应的实体类
	 * @param map
	 *            指后面request中接收的map,如request.getParameters()，返回的就是map对象
	 * @return
	 * @throws Exception
	 */
	public static Object getFormBean(String beanPrifex, Class beanClass, Map map)
			throws Exception {
		Object bean = null;
		if (map == null || map.size() < 1)
			return bean;
		try {
			Method[] methods = beanClass.getDeclaredMethods();
			if (methods == null || methods.length < 1)
				return null;
			bean = beanClass.newInstance();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().startsWith("get")) {
					String propname = methods[i].getName().substring(4,
							methods[i].getName().length());
					propname = methods[i].getName().substring(3, 4)
							.toLowerCase()
							+ propname;
					String[] svalue = (String[]) map.get(beanPrifex + "."
							+ propname);
					if (svalue == null || svalue.length < 1
							|| "".equals(svalue[0]))
						continue;
					if (methods[i].getReturnType() == java.util.Date.class) {
						java.util.Date dt = MiscUtil.getDateByString(svalue[0]
								.toString(), MiscUtil.FMT_DATE_YYYY_MM_DD);
						PropertyUtils.setProperty(bean, propname, dt);
						continue;
					} else if (methods[i].getReturnType() == java.lang.Double.class) {
						// 去掉金额字段千分位
						svalue[0] = svalue[0].replaceAll(",", "");
						PropertyUtils.setProperty(bean, propname, Double
								.valueOf(svalue[0].toString()));
						continue;
					} else if (methods[i].getReturnType() == java.lang.Long.class) {
						PropertyUtils.setProperty(bean, propname, Long
								.valueOf(svalue[0].toString()));
						continue;
					} else if (methods[i].getReturnType() == java.lang.Integer.class) {
						PropertyUtils.setProperty(bean, propname, Integer
								.valueOf(svalue[0].toString()));
						continue;
					}
					PropertyUtils.setProperty(bean, propname, svalue[0]
							.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bean;
	}

	/**
	 * 将request中的列表数据封装在List数据结构中
	 * 
	 * @param listBeanPrefix
	 *            Web页面中引用的bean简写，如,user.name,这里指的是user
	 * @param beanClass
	 *            指javabean 所对应的实体类
	 * @param map
	 *            指后面request中接收的map,如request.getParameters()，返回的就是map对象
	 * @return
	 * @throws Exception
	 */
	public static List getFormBeans(String listBeanPrefix, Class beanClass,
			Map map) throws Exception {
		List beanList = new ArrayList();
		if (map == null || map.size() < 1)
			return beanList;
		try {
			Method[] methods = beanClass.getDeclaredMethods();
			if (methods == null || methods.length < 1)
				return null;

			// 循环数据列
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().startsWith("get")) {
					String propname = methods[i].getName().substring(4,
							methods[i].getName().length());
					propname = methods[i].getName().substring(3, 4)
							.toLowerCase()
							+ propname;
					Object[] svalue = (String[]) map.get(listBeanPrefix + "."
							+ propname);
					if (svalue == null || svalue.length < 1)
						continue;

					if (beanList.size() < 1) {
						for (int n = 0; n < svalue.length; n++) {
							beanList.add(beanClass.newInstance());
						}
					}

					// 将每列的值，按行顺序，依次填充到实体bean中
					for (int j = 0; j < svalue.length; j++) {
						if (svalue[j] == null || "".equals(svalue[j]))
							continue;
						if (methods[i].getReturnType() == java.util.Date.class) {
							PropertyUtils.setProperty(beanList.get(j),
									propname, MiscUtil.getDateByString(
											svalue[j].toString(),
											MiscUtil.FMT_DATE_YYYY_MM_DD));
							continue;
						} else if (methods[i].getReturnType() == java.lang.Double.class) {
							// 去掉金额字段千分位
							svalue[j] = svalue[j].toString()
									.replaceAll(",", "");
							PropertyUtils.setProperty(beanList.get(j),
									propname, Double.valueOf(svalue[j]
											.toString()));
							continue;
						} else if (methods[i].getReturnType() == java.lang.Long.class) {
							PropertyUtils.setProperty(beanList.get(j),
									propname, Long
											.valueOf(svalue[j].toString()));
							continue;
						} else if (methods[i].getReturnType() == java.lang.Integer.class) {
							PropertyUtils.setProperty(beanList.get(j),
									propname, Integer.valueOf(svalue[j]
											.toString()));
							continue;
						}
						PropertyUtils.setProperty(beanList.get(j), propname,
								svalue[j].toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return beanList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Object svalue = "2012-02-09";
		java.util.Date dt = MiscUtil.getDateByString((String) svalue,
				MiscUtil.FMT_DATE_YYYY_MM_DD);
		System.out.println(dt);
	}

}
