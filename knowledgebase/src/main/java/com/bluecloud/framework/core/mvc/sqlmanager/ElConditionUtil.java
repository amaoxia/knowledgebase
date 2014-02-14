package com.bluecloud.framework.core.mvc.sqlmanager;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * el manage
 * 
 * @author dafei
 * 
 */
public class ElConditionUtil {
	/**
	 * 鏄惁涓虹┖
	 */
	private final static String IS_NULL = "isNull";

	/**
	 * 闈炵┖
	 */
	private final static String NOT_NULL = "notNull";

	/**
	 * 灏忎簬 <
	 */
	private final static String LESS_THAN = "lt";

	/**
	 * 澶т簬 >
	 */
	private final static String GREATER_THAN = "gt";

	/**
	 * 灏忎簬绛変簬
	 */
	private final static String LESS_EQUEAL = "le";

	/**
	 * 澶т簬绛変簬
	 */
	private final static String GREATER_EQUEAL = "ge";

	/**
	 * 杩炴帴绗�
	 */
	private final static String AND = "and";

	/**
	 * 杞崲鍚湁isNull 鐨� 瀛楃涓�
	 * 
	 * @param empty
	 * @param parsMap
	 * @return
	 * @throws NoEmptyPropertyException
	 * @throws ELParseException
	 * @throws SqlException
	 */
	private static boolean transformEmpty(String empty,
			Map<String, Object> parsMap) throws SqlException {
		String[] filterEmpty = empty.trim().split("\\s+");
		if (filterEmpty.length != 2) {
			throw new SqlException("the condition length should be two");
		}
		if (!filterEmpty[0].equals(IS_NULL)) {
			throw new SqlException("no \"isNull\" property");
		}
		String condition = getElContent(filterEmpty[1]);
		if (parsMap.get(condition) == null) {
			return true;
		}
		return false;
	}

	/**
	 * 杞崲鍚湁 notNull 鐨勬潯浠�
	 * 
	 * @param notEmpty
	 * @param parsMap
	 * @return
	 * @throws SqlException
	 * @throws NoEmptyPropertyException
	 */
	private static boolean transformNotEmpty(String notEmpty,
			Map<String, Object> parsMap) throws SqlException {
		String[] filterEmpty = notEmpty.trim().split("\\s+");
		if (filterEmpty.length != 2) {
			throw new SqlException("the condition:" + notEmpty
					+ " length should be two");
		}
		if (!filterEmpty[0].equals(NOT_NULL)) {
			throw new SqlException("no \"notNull\" property");
		}
		String condition = getElContent(filterEmpty[1]);
		if (parsMap.get(condition) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 杞崲灏忎簬 lt 鐨勬潯浠�
	 * 
	 * @param ltString
	 * @param parsMap
	 * @return
	 * @throws SqlException
	 * @throws ParseException
	 */
	private static boolean transformLT(String ltString,
			Map<String, Object> parsMap) throws SqlException, ParseException {
		String[] filter = ltString.trim().split("\\s+");
		if (filter.length != 3) {
			throw new SqlException("the condition length should be 3");
		} else if (!filter[1].equals(LESS_THAN)) {
			throw new SqlException("no \"lt\" property");
		} else if (!isNumeric(filter[2])) {
			throw new SqlException("the el[2]:" + filter[2] + " must be number");
		}
		NumberFormat format = NumberFormat.getInstance();
		String condition = getElContent(filter[0]);
		String value = mapTranform(parsMap.get(condition));
		if (value == null) {
			throw new SqlException("key :" + condition + " no value in parsMap");
		} else if (format.parse(value).doubleValue() < format.parse(filter[2])
				.doubleValue()) {
			return true;
		}
		return false;
	}

	/**
	 * 杞崲澶т簬  gt
	 * @param ltString
	 * @param parsMap
	 * @return
	 * @throws SqlException
	 * @throws ParseException
	 */
	private static boolean transformGT(String gtString,
			Map<String, Object> parsMap) throws SqlException, ParseException {
		String[] filter = gtString.trim().split("\\s+");
		if (filter.length != 3) {
			throw new SqlException("the condition length should be 3");
		} else if (!filter[1].equals(GREATER_THAN)) {
			throw new SqlException("no \"gt\" property");
		} else if (!isNumeric(filter[2])) {
			throw new SqlException("the el[2]:" + filter[2] + " must be number");
		}
		NumberFormat format = NumberFormat.getInstance();
		String condition = getElContent(filter[0]);
		String value = mapTranform(parsMap.get(condition));
		if (value == null) {
			throw new SqlException("key :" + condition + " no value in parsMap");
		} else if (format.parse(value).doubleValue() > format.parse(filter[2])
				.doubleValue()) {
			return true;
		}
		return false;
	}

	/**
	 * 杞崲灏忎簬绛変簬 le
	 * @param leString
	 * @param parsMap
	 * @return
	 * @throws SqlException
	 * @throws ParseException
	 */
	private static boolean transformLE(String leString,
			Map<String, Object> parsMap) throws SqlException, ParseException {
		String[] filter = leString.trim().split("\\s+");
		if (filter.length != 3) {
			throw new SqlException("the condition length should be 3");
		} else if (!filter[1].equals(LESS_EQUEAL)) {
			throw new SqlException("no \"le\" property");
		} else if (!isNumeric(filter[2])) {
			throw new SqlException("the el[2]:" + filter[2] + " must be number");
		}
		NumberFormat format = NumberFormat.getInstance();
		String condition = getElContent(filter[0]);
		String value = mapTranform(parsMap.get(condition));
		if (value == null) {
			throw new SqlException("key :" + condition + " no value in parsMap");
		} else if (format.parse(value).doubleValue() <= format.parse(filter[2])
				.doubleValue()) {
			return true;
		}
		return false;
	}

	/**
	 * 杞崲澶т簬绛変簬 ge
	 * @param geString
	 * @param parsMap
	 * @return
	 * @throws SqlException
	 * @throws ParseException
	 */
	private static boolean transformGE(String geString,
			Map<String, Object> parsMap) throws SqlException, ParseException {
		String[] filter = geString.trim().split("\\s+");
		if (filter.length != 3) {
			throw new SqlException("the condition length should be 3");
		} else if (!filter[1].equals(GREATER_EQUEAL)) {
			throw new SqlException("no \"ge\" property");
		} else if (!isNumeric(filter[2])) {
			throw new SqlException("the el[2]:" + filter[2] + " must be number");
		}
		NumberFormat format = NumberFormat.getInstance();
		String condition = getElContent(filter[0]);
		String value = mapTranform(parsMap.get(condition));
		if (value == null) {
			throw new SqlException("key :" + condition + " no value in parsMap");
		} else if (format.parse(value).doubleValue() >= format.parse(filter[2])
				.doubleValue()) {
			return true;
		}
		return false;
	}

	/**
	 * get ${el} value el
	 * 
	 * @param elString
	 * @return
	 */
	private static String getElContent(String elString) {
		String filterEl = elString.trim().substring(2, elString.length() - 1);
		return filterEl;
	}

	/**
	 * 鍒ゆ柇涓�涓猄tring鏄惁涓烘暣鏁�
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		String regex = "-?[0-9]+\\.?[0-9]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();

	}

	/**
	 * 鏍规嵁鏉′欢杩斿洖杞崲鍊�
	 * 
	 * @param condition
	 * @param parsMap
	 * @return
	 * @throws SqlException
	 * @throws NoEmptyPropertyException
	 * @throws ParseException
	 */
	public static boolean transform(String condition,
			Map<String, Object> parsMap) throws SqlException, ParseException {
		// 鍒ゆ柇 isNull or notNull or others 鏉′欢 寰楀埌涓猼rue or false
		if (condition.trim().startsWith(IS_NULL)) {
			return transformEmpty(condition, parsMap);
		} else if (condition.trim().startsWith(NOT_NULL)) {
			return transformNotEmpty(condition, parsMap);
		} else if (condition.indexOf(AND) != -1) {
			String[] ands = condition.split(AND);
			return (ifOperation(ands[0], parsMap) && ifOperation(condition,
					parsMap));
		} else {
			return ifOperation(condition, parsMap);
		}

	}

	/**
	 * if寰幆
	 * @param condition
	 * @param parsMap
	 * @return
	 * @throws SqlException
	 * @throws ParseException
	 */
	private static boolean ifOperation(String condition,
			Map<String, Object> parsMap) throws SqlException, ParseException {
		if (condition.indexOf(LESS_THAN) != -1) {
			return transformLT(condition, parsMap);
		} else if (condition.indexOf(GREATER_THAN) != -1) {
			return transformGT(condition, parsMap);
		} else if (condition.indexOf(LESS_EQUEAL) != -1) {
			return transformLE(condition, parsMap);
		} else if (condition.indexOf(GREATER_EQUEAL) != -1) {
			return transformGE(condition, parsMap);
		} else {
			throw new SqlException("'" + condition + "' no control key");

		}
	}

	/**
	 * map绫�
	 * @param obj
	 * @return
	 */
	public static String mapTranform(Object obj) {
		String value = "";
		if (obj instanceof String) {
			value = "'" + (String) obj + "'";
		} else if (obj instanceof Integer) {
			value = ((Integer) obj).toString();
		} else if (obj instanceof Double) {
			value = ((Double) obj).toString();
		} else if (obj instanceof String[]) {
			int i = 0;
			for (String pars : (String[]) obj) {
				if (i == 0) {
					value += "(" + pars;
					i += 1;
				} else if (i == ((String[]) obj).length - 1) {
					value += "," + pars + ")";
				}
			}
		}
		return value;
	}

}