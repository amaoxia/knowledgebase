package com.bluecloud.framework.utils;

import com.bluecloud.framework.utils.DateUtil;
/**
 *
 * @author dafei
 *
 */
public class PrimarykeyGenerate {
	
	/**
	 * 
	* <p>方法名称: genBillCode|描述: 生成流水单号:标识码+yyMMddHHmmss+毫秒后4位</p>
	* @param sflag 标识码 B:订单  P:配送单 D:兑换单
	* @return
	 */
	public static String genPrimarykey(String sflag) {
		String timeStamp = DateUtil.serverCurrentTimeStamp(DateUtil.ORA_DATE_TIME_SHORT_FORMAT);
		String timeMillis = System.currentTimeMillis() + "";
		timeMillis = timeMillis.substring(timeMillis.length()-4, timeMillis.length());
		return sflag + timeStamp + timeMillis;
	}
}
