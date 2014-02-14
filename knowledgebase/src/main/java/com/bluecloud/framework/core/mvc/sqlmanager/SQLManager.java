package com.bluecloud.framework.core.mvc.sqlmanager;

import java.io.File;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.bluecloud.framework.utils.MiscUtil;

/**
 * @author dafei
 *
 */
public final class SQLManager {
	private SQLManager() {
	}

	final static String XML_PREFIX = "sql_";
	public static Map sqlmap = null;
	public static Map keymap = null;
	public static StringBuffer msg = new StringBuffer();

	/**
	 * id 的属性 sql 
	 */
	private final static String ID_SQL = "sql";
	/**
	 * id 的属性 if 
	 */
	private final static String ID_IF = "if";
	/**
	 * if 的属性 test 
	 */
	private final static String IF_TEST = "test";

	/**
	 * 通过id 得到缓存中的sql
	 * @param id
	 * @return
	 * @throws SqlException
	 * @throws ParseException
	 */
	public static String getSql(String id) throws SqlException, ParseException {
		return getSql(id, null);
	}

	/**
	 * @param id
	 * @param parsMap
	 * @return
	 * @throws NoEmptyPropertyException
	 * @throws SqlException
	 * @throws ParseException
	 */
	public static String getSql(String id, Map<String, Object> parsMap)
			throws SqlException, ParseException {
		StringBuffer valueBuffer = new StringBuffer();
		String sql = getSql(id, parsMap, valueBuffer);
		String values[] = valueBuffer.toString().split(";");
		String regex = "\\?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sql);
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		while (matcher.find()) {
			matcher.appendReplacement(buffer, values[i]);
			i += 1;
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	/**
	 * 通过id 得到sql
	 * @param id  想要得到的sql的id
	 * @param parsMap 存储了参数值
	 * @param valueBuffer 预处理存储值
	 * @return
	 * @throws SqlException 
	 * @throws NoEmptyPropertyException 
	 * @throws ParseException 
	 */
	public static String getSql(String id, Map<String, Object> parsMap,
			StringBuffer valueBuffer) throws SqlException, ParseException {
		String condition = null;
		String textValue = null;
		String sql = "";
		Node node = null;
		List<Element> elements = (List) SQLManager.sqlmap.get(id);

		for (Element element : elements) {
			node = element.detach();
			String nodeName = node.getName();
			if (nodeName.equals(ID_SQL)) {
				sql = LableUtil.filterEl(node.getStringValue(), parsMap);
			} else if (nodeName.equals(ID_IF)) {
				condition = element.attributeValue(IF_TEST);
				textValue = node.getStringValue();
				sql += " "
						+ LableUtil.fileterIf(condition, textValue, parsMap,
								valueBuffer);
			}
		}
		return sql;
	}

	/**
	 * 动态将数据库记录映射到指定的实体中
	 * @param resultList
	 * @param entityClass
	 * @param props
	 * @return
	 * @throws Exception
	 */
	public static List mappingList(ResultSet rsResult, Class entityClass)
			throws Exception {
		List resultList = new ArrayList();
		List<String> cellList = new ArrayList();
		try {
			ResultSetMetaData rsMetaData = rsResult.getMetaData();
			int keylen = rsMetaData.getColumnCount();
			for (int j = 1; j <= keylen; j++) {
				String asName = rsMetaData.getColumnLabel(j);
				if (asName == null || "".equals(asName)) {
					cellList.add(rsMetaData.getColumnName(j));
					continue;
				}
				cellList.add(asName);
			}
			//匹配对象属性
			String[] props = matchObjectProp(entityClass, cellList);
			if (props == null)
				return resultList;
			while (rsResult.next()) {
				Object entityobj = entityClass.newInstance();
				for (int i = 0; i < props.length; i++) {
					if (props[i] == null)
						continue;
					Object svalue = rsResult.getObject(cellList.get(i)
							.toString());
					if (svalue == null || "".equals(svalue.toString()))
						continue;
					else if (svalue instanceof java.util.Date) {
						java.util.Date dt = MiscUtil.getDateByString(svalue
								.toString(), MiscUtil.FMT_DATE_YYYYMMDDHHmmss);
						PropertyUtils.setProperty(entityobj, props[i], dt);
						//BeanUtils.copyProperty(entityobj, props[i], dt);
						continue;
					}
					BeanUtils.copyProperty(entityobj, props[i], svalue);
				}
				resultList.add(entityobj);
			}
		} catch (Exception e) {
			throw e;
		}
		return resultList;
	}

	/**
	 * 将数据库记录映射到对应的实体中
	 * @param rsResult
	 * @param entityName
	 * @return
	 * @throws Exception
	 */
	public static List mappingList(List resultList, Class entityClass,
			String[] props) throws Exception {
		List items = new ArrayList();
		try {
			if (resultList == null || resultList.size() < 1)
				return null;
			for (int i = 0; i < resultList.size(); i++) {
				Object[] values = (Object[]) resultList.get(i);
				Object entityobj = entityClass.newInstance();
				for (int j = 0; j < props.length; j++) {
					BeanUtils.copyProperty(entityobj, props[j], values[j]);
				}
				items.add(entityobj);
			}
		} catch (Exception e) {
			throw e;
		}
		return items;
	}

	/**
	 * 初始化系统中所有含有sql的xml文件，并加载到缓存map中
	 * @param dirName
	 * @throws Exception
	 */
	public static void initSqlMap(String dirname) throws Exception {
		if (isNullOrEmpty(dirname))
			return;
		List filelist = new ArrayList();
		refreshFileList(dirname.replaceAll("\\\\", "/"), filelist);
		if (filelist == null || filelist.size() < 1)
			return;
		try {
			for (int i = 0; i < filelist.size(); i++) {
				String sfilename = filelist.get(i).toString();
				putSqlByFilename(sfilename.replaceAll("\\\\", "/"));
			}
		} catch (Exception e) {
			System.out.println(msg.toString());
			e.printStackTrace();
		}
	}

	/**
	 * @param filename
	 * @throws Exception
	 */
	private static void putSqlByFilename(String filename) throws Exception {
		if (keymap == null)
			keymap = new HashMap();
		if (sqlmap == null)
			sqlmap = new HashMap();
		List list = parseXml(filename);
		if (list == null || list.size() < 1)
			return;
		for (int i = 0; i < list.size(); i++) {
			Element element = (Element) list.get(i);
			if (element.elements() == null || element.elements().size() < 1)
				continue;
			//String sql=element.getTextTrim();
			//if(isNullOrEmpty(sql)) continue;

			String skey = element.attributeValue("id");
			if (keymap.get(skey) != null) {
				msg.append("ID为:" + keymap.get(skey).toString() + "的文件:"
						+ filename + "与ID为:" + skey + "的文件存在相同ID.");
				continue;
			}
			keymap.put(skey, filename);
			sqlmap.put(skey, element.elements());
		}
	}

	/**
	 * @param filename     c:/catalog/catalog.xml
	 * @return
	 * @throws Exception
	 */
	private static List parseXml(String filename) throws Exception {
		SAXReader saxReader = new SAXReader();
		Document docu = saxReader.read(new File(filename));
		return docu.getRootElement().elements();
	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || "".equals(s);
	}

	private static void refreshFileList(String strPath, List filelist) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();
		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				refreshFileList(files[i].getAbsolutePath(), filelist);
			} else {
				String strFileName = files[i].getAbsolutePath().toLowerCase();
				if (strFileName.endsWith(".xml")
						&& files[i].getName().startsWith(XML_PREFIX)) {
					System.out.println("---" + strFileName);
					filelist.add(files[i].getAbsolutePath());
				}
			}
		}
	}

	/**
	 * 将数据库字段与对象属性进行匹配
	 * @param obj
	 * @param columns
	 * @return
	 * @throws Exception
	 */
	private static String[] matchObjectProp(Class cls, List columns)
			throws Exception {
		String[] props = new String[columns.size()];
		Method[] methods = cls.getMethods();
		for (int n = 0; n < props.length; n++) {
			for (int m = 0; m < methods.length; m++) {
				String funcname = methods[m].getName();
				if (funcname.startsWith("set"))
					continue;
				funcname = funcname.substring(3, funcname.length());
				if (columns.get(n).toString().toUpperCase().equals(
						funcname.toUpperCase())) {
					props[n] = funcname.substring(0, 1).toLowerCase()
							+ funcname.substring(1, funcname.length());
				}
			}
		}
		return props;
	}

	public static void main(String[] args) throws Exception {
		//String dir="D:/kyhuaqing/yqsys/Src/WebRoot/WEB-INF/classes//com/operation/news/entity";
		//initSqlMap(dir);
		System.out.println(String.class.getName());
	}
}
