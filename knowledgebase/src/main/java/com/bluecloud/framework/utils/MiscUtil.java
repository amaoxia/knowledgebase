package com.bluecloud.framework.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public final class MiscUtil {
	public static String formatDigit(long l, int width) {
		String t = l + "";
		if (t.length() < width) {
			int count = width - t.length();
			for (int i = 0; i < count; ++i) {
				t = "0" + t;
			}
		}
		return t;
	}

	public static String formatDigit(int i, int w) {
		long l = (long) i;
		return formatDigit(l, w);
	}

	public static String formatDigit(String s, int w) {
		long l = 0L;
		try {
			l = Long.parseLong(s);
		} catch (Exception e) {
		}
		return formatDigit(l, w);
	}

	/**
	 * FMT_DATE_YYYY_MM_DD 锟斤拷锟斤拷 2003-01-01 FMT_DATE_YYYYMMDD 锟斤拷锟斤拷 20030101 the
	 * following const is to define date format.
	 */
	public static final int FMT_DATE_YYYY_MM_DD = 1;

	public static final int FMT_DATE_YYYYMMDD = 2;

	public static final int FMT_DATE_YYMMDD = 3;

	public static final int FMT_DATE_YYYY = 4;

	public static final int FMT_DATE_YYMM = 5;

	public static final int FMT_DATE_YYYYMM = 6;

	public static final int FMT_DATE_YYYYMMDDHHmmss = 7;

	public static final int FMT_DATE_YYYYMMDDHHmmss1 = 8;

	public static final int FMT_DATE_YYYYMMDD9 = 9;

	public static final int FMT_DATE_MMDD = 10;

	public static final int FMT_TIME_HH = 11;

	public static final int FMT_TIME_mm = 12;

	public static final int FMT_DATE_YYYYMM2 = 13;

	/**
	 * this function is to format date into a string @ param date the date to be
	 * formatted.
	 * @param nFmt
	 *            FMT_DATE_YYYYMMDD to format string like 'yyyy-mm-dd' or to
	 *            format string like 'yyyy-mm-dd hh:mm:ss'
	 * @return String
	 */
	public static String formatDate(Date date, int nFmt) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat fmtDate = null;
		switch (nFmt) {
		default:
		case MiscUtil.FMT_DATE_YYYY_MM_DD:
			fmtDate = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case MiscUtil.FMT_DATE_YYYYMMDD:
			fmtDate = new SimpleDateFormat("yyyyMMdd");
			break;
		case MiscUtil.FMT_DATE_YYMMDD:
			fmtDate = new SimpleDateFormat("yyMMdd");
			break;
		case MiscUtil.FMT_DATE_YYYY:
			fmtDate = new SimpleDateFormat("yyyy");
			break;
		case MiscUtil.FMT_DATE_YYMM:
			fmtDate = new SimpleDateFormat("yyMM");
			break;
		case MiscUtil.FMT_DATE_YYYYMM:
			fmtDate = new SimpleDateFormat("yyyyMM");
			break;
		case MiscUtil.FMT_DATE_YYYYMMDDHHmmss:
			fmtDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case MiscUtil.FMT_DATE_YYYYMMDDHHmmss1:
			fmtDate = new SimpleDateFormat("yyyyMMddHHmmss");
			break;
		case MiscUtil.FMT_DATE_YYYYMMDD9:
			fmtDate = new SimpleDateFormat("yyyy/MM/dd");
			break;
		case MiscUtil.FMT_DATE_MMDD:
			fmtDate = new SimpleDateFormat("MMdd");
			break;
		case MiscUtil.FMT_TIME_HH:
			fmtDate = new SimpleDateFormat("HH");
			break;
		case MiscUtil.FMT_TIME_mm:
			fmtDate = new SimpleDateFormat("mm");
			break;
		case MiscUtil.FMT_DATE_YYYYMM2:
			fmtDate = new SimpleDateFormat("yyyy/MM");
			break;
		}
		return fmtDate.format(date);
	}

	/**
	 * 锟斤拷锟街段达拷转锟斤拷为锟斤拷锟斤拷锟斤拷锟酵革拷式
	 * 
	 * @param strDate
	 * @return Date
	 */
	public static Date getDateByString(String strDate, int nFmt) {
		SimpleDateFormat fmtDate;
		java.util.Date dDate = null;
		switch (nFmt) {
		default:
		case MiscUtil.FMT_DATE_YYYY_MM_DD:
			fmtDate = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case MiscUtil.FMT_DATE_YYYYMMDD:
			fmtDate = new SimpleDateFormat("yyyyMMdd");
			break;
		case MiscUtil.FMT_DATE_YYMMDD:
			fmtDate = new SimpleDateFormat("yyMMdd");
			break;
		case MiscUtil.FMT_DATE_YYYY:
			fmtDate = new SimpleDateFormat("yyyy");
			break;
		case MiscUtil.FMT_DATE_YYMM:
			fmtDate = new SimpleDateFormat("yyMM");
			break;
		case MiscUtil.FMT_DATE_YYYYMM:
			fmtDate = new SimpleDateFormat("yyyyMM");
			break;
		case MiscUtil.FMT_DATE_YYYYMMDDHHmmss:
			fmtDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case MiscUtil.FMT_DATE_YYYYMMDDHHmmss1:
			fmtDate = new SimpleDateFormat("yyyyMMddHHmmss");
			break;
		case MiscUtil.FMT_DATE_YYYYMM2:
			fmtDate = new SimpleDateFormat("yyyy/MM");
			break;
		}
		try {
			dDate = fmtDate.parse(strDate);
		} catch (Exception e) {
			dDate = null;
		}
		return dDate;
	}

	/**
	 * 锟矫碉拷锟斤拷 N 锟斤拷锟斤拷.
	 * 
	 * @author weiliao
	 * @param String
	 *            锟斤拷 200402
	 * @param int
	 *            锟斤拷 3
	 * @return String 锟矫碉拷锟斤拷 200311
	 */
	static public String getPreviousAppointMonth(String strThisMonth,
			int lAppointMonth) {
		String strYear = "";
		String strMonth = "";
		long lYear = 0;
		long lMonth = 0;
		String strReturn = "";
		try {
			if (!"".equals(strThisMonth)) {
				// 转锟斤拷之前锟斤拷 year 锟斤拷 month
				strYear = strThisMonth.substring(0, 4);
				strMonth = strThisMonth.substring(4, 6);
				lYear = Long.valueOf(strYear).longValue();
				lMonth = Long.valueOf(strMonth).longValue();
				// 转锟斤拷锟叫碉拷
				lMonth = lMonth - lAppointMonth;
				while (lMonth <= 0) {
					lMonth = lMonth + 12;
					lYear = lYear - 1;
				}
				// 转锟斤拷锟斤拷锟�
				if (lMonth < 10) {
					strMonth = "0" + lMonth;
				} else {
					strMonth = String.valueOf(lMonth);
				}
				strReturn = String.valueOf(lYear) + strMonth;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return strReturn;
	}

	/**
	 * 锟矫碉拷锟斤拷一锟斤拷锟斤拷.
	 * 
	 * @author weiliao
	 * @param String
	 *            锟斤拷 200401
	 * @return String 锟矫碉拷锟斤拷 200312
	 */
	static public String getPreviousMonth(String strThisMonth) {
		String strYear = "";
		String strMonth = "";
		String strReturn = "";
		try {
			if (!"".equals(strThisMonth)) {
				strYear = strThisMonth.substring(0, 4);
				strMonth = strThisMonth.substring(4, 6);
				// strReturn = strYear + strMonth;
				if ("01".equals(strMonth)) {
					strMonth = "12";
					strYear = String
							.valueOf(Long.valueOf(strYear).longValue() - 1);
				} else {
					strMonth = String.valueOf(Long.valueOf(strMonth)
							.longValue() - 1);
					if (strMonth.length() < 2) {
						strMonth = "0" + strMonth;
					}
				}
			}
			strReturn = strYear + strMonth;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return strReturn;
	}

	/**
	 * 锟矫碉拷去锟斤拷模锟斤拷锟斤拷路锟�
	 * 
	 * @author weiliao
	 * @param String
	 *            锟斤拷 200401
	 * @return String 锟矫碉拷锟斤拷 200312
	 */
	static public String getPreviousDec(String strThisMonth) {
		return (Long.parseLong(strThisMonth.substring(0, 4)) - 1) + "12";
	}

	/**
	 * 锟斤拷锟斤拷N锟斤拷之锟斤拷锟斤拷锟斤拷锟�
	 * 
	 * @param bgdate
	 * @param days
	 * @return
	 */
	public static Date getAfterDate(Date bgdate, int days) {
		long Time = (bgdate.getTime() / 1000) + 60 * 60 * 24 * days;
		Date ret = new Date();
		ret.setTime(Time * 1000);
		return ret;
	}

	/**
	 * 锟斤拷锟斤拷N锟斤拷之前锟斤拷锟斤拷锟斤拷
	 * @return
	 */
	public static Date getBeforeDay(String cdate, int days) throws Exception {
		if (days < 1)
			return new Date();
		BeforeDate bfdate = new BeforeDate(cdate.replaceAll("-", ""), days);
		return getDateByString(bfdate.getBeforeDayString(), 2);
	}

	/**
	 * 锟矫碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷之锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * 
	 * @param rq1
	 * @param rq2
	 * @return
	 */
	public static long DaysBetween(Date bgdate, Date enddate) {
		long beginTime = bgdate.getTime();
		long endTime = enddate.getTime();
		long days = (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24) + 0.5);
		return days;
	}

	/**
	 * 锟矫碉拷锟斤拷锟斤拷锟斤拷
	 * @param sdate
	 * @return
	 * @throws Exception
	 */
	public static String[] getWeekDate(String sdate) throws Exception {
		String[] dates = new String[2];
		int wdnum = MiscUtil.getWeekNum(sdate);
		if (wdnum == 1) {
			dates[0] = sdate;
			dates[1] = MiscUtil.formatDate(MiscUtil.getAfterDate(MiscUtil
					.getDateByString(sdate, 1), 6), 1);
		} else {
			dates[0] = MiscUtil.formatDate(MiscUtil.getBeforeDay(sdate,
					wdnum - 1), 1);
			dates[1] = MiscUtil.formatDate(MiscUtil.getAfterDate(MiscUtil
					.getDateByString(dates[0], 1), 6), 1);
		}
		return dates;
	}

	/**
	 * 锟矫碉拷锟斤拷前系统时锟斤拷姆锟斤拷锟�
	 * 
	 * @return
	 */
	public static int getIntMinuteByToday() {

		Date dtToday = getDateByString(formatDate(new Date(),
				FMT_DATE_YYYYMMDDHHmmss), FMT_DATE_YYYYMMDDHHmmss);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtToday);
		int hour = cal.get(Calendar.MINUTE);
		return hour;
	}

	/**
	 * 锟斤拷荽锟斤拷锟�锟斤拷锟斤拷锟节革拷式锟街凤拷玫锟斤拷锟斤拷诩锟�
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekByString(String dt) {
		Date dtToday = getDateByString(dt, FMT_DATE_YYYYMMDD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtToday);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return getWeekNum(day);
	}

	public static int getWeekNum(String dt) {
		Date dtToday = getDateByString(dt, FMT_DATE_YYYYMMDD);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dtToday);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 锟斤拷荽锟斤拷锟�锟斤拷锟斤拷锟节革拷式锟矫碉拷锟斤拷锟节硷拷
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekByDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return getWeekNum(day);
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷址锟斤拷锟斤拷锟斤拷诩锟斤拷址锟�
	 * 
	 * @param day
	 * @return
	 */
	public static String getWeekNum(int day) {
		String weekNum = "锟斤拷锟斤拷";
		switch (day) {
		case 1:
			weekNum += "一";
			break;
		case 2:
			weekNum += "锟斤拷";
			break;
		case 3:
			weekNum += "锟斤拷";
			break;
		case 4:
			weekNum += "锟斤拷";
			break;
		case 5:
			weekNum += "锟斤拷";
			break;
		case 6:
			weekNum += "锟斤拷";
			break;
		case 7:
			weekNum += "锟斤拷";
			break;
		}
		return weekNum;
	}

	//锟斤拷取锟斤拷一锟杰碉拷锟斤拷锟斤拷

	public Date nextWeek(Date currentDate) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.add(GregorianCalendar.DATE, 7);//锟斤拷锟斤拷锟斤拷锟较硷拷7锟斤拷
		return cal.getTime();

	}

	//锟斤拷取锟斤拷锟斤拷锟秸碉拷锟斤拷锟斤拷
	public Date getSunday(Date monday) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(monday);
		cal.add(GregorianCalendar.DATE, 6);//锟斤拷锟斤拷锟斤拷锟较硷拷6锟斤拷
		return cal.getTime();

	}

	/**
	 * 转锟斤拷锟街凤拷
	 * 
	 * @param str
	 * @return String
	 * @throws Exception
	 */
	public static String transferString(String str) throws Exception {
		StringBuffer sbResult = new StringBuffer();
		char[] cFilter = { '\'', '\"', '\\' };
		if ((str != null) && (!str.equals(""))) {
			for (int i = 0; i < str.length(); i++) {
				for (int j = 0; j < cFilter.length; j++) {
					if (str.charAt(i) == cFilter[j]) {
						sbResult.append("\\");
					}
				}
				sbResult.append(str.charAt(i));
			}
		}
		return sbResult.toString();
	}

	/**
	 * 锟斤拷式锟斤拷锟叫憋拷慕锟斤拷
	 * 
	 * @param dAmount
	 *            锟斤拷锟�
	 * @param lSacle
	 *            jingdu
	 * @return
	 */
	public static String formatAmount(double dAmount, int lSacle) {
		String strData = new java.math.BigDecimal(java.lang.Math.abs(dAmount))
				.setScale(lSacle, java.math.BigDecimal.ROUND_HALF_UP)
				.toString();
		// 锟斤拷小锟斤拷锟角帮拷秃锟斤拷锟斤拷莘直锟饺★拷锟�
		int nPoint;
		nPoint = strData.indexOf(".");
		String strFront = new String(strData), strEnd = "";
		if (nPoint != -1) {
			strFront = strData.substring(0, nPoint);
			strEnd = strData.substring(nPoint + 1, strData.length());
		}
		String strTemp = "";
		// 小锟斤拷锟角帮拷锟斤拷锟斤拷菁锟�,"
		strTemp = new String(strFront);
		strFront = "";
		int nNum, i;
		nNum = 0;
		for (i = strTemp.length() - 1; i >= 0; i--) {
			if (nNum == 3) {
				strFront = "," + strFront;
				nNum = 0;
			}
			nNum++;
			char cData;
			cData = strTemp.charAt(i);
			strFront = cData + strFront;
		}

		// 锟斤拷锟斤拷锟揭★拷锟轿�
		if (lSacle > 0) {
			strData = strFront + "." + strEnd;
		}
		// 锟斤拷锟斤拷
		else {
			strData = strFront;
		}
		if (dAmount < 0 && !strData.equals("0.00")) {
			strData = "-" + strData;
		}
		return strData;
	}

	/**
	 * 锟斤拷式锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷 lScale 位锟斤拷锟斤拷锟斤拷锟斤拷锟角帮拷娌癸拷锟�锟斤拷锟斤拷 (33,3) 锟矫碉拷 "033"
	 * 
	 * @param dAmount
	 *            锟斤拷锟�
	 * @param lSacle
	 *            jingdu
	 * @return
	 */
	public static String formatAmountInt(int dAmount, int lScale) {
		String strTmp = "";
		StringBuffer strReturn = new StringBuffer();
		int iTmp = 0;
		strTmp = String.valueOf(dAmount);
		if ((iTmp = strTmp.length()) < lScale) {
			for (int i = 0; i < lScale - iTmp; i++) {
				strReturn.append("0");
			}
		}
		strReturn.append(strTmp);
		return strReturn.toString();
	}

	/**
	 * 锟斤拷式锟斤拷锟叫憋拷慕锟斤拷(锟斤拷锟斤拷千锟斤拷位",") 锟斤拷要锟斤拷锟节憋拷锟斤拷锟斤拷锟绞�
	 * 
	 * @param dAmount
	 *            锟斤拷锟�
	 * @param lSacle
	 *            jingdu
	 * @return
	 */
	public static String formatAmount2(double dAmount, int lSacle) {
		// 锟斤拷dAmount==0.0时,锟斤拷锟截空达拷
		if (dAmount > -0.000000001 && dAmount < 0.0000001)
			return "";
		String strData = new java.math.BigDecimal(java.lang.Math.abs(dAmount))
				.setScale(lSacle, java.math.BigDecimal.ROUND_HALF_UP)
				.toString();
		// 锟斤拷小锟斤拷锟角帮拷秃锟斤拷锟斤拷莘直锟饺★拷锟�
		int nPoint;
		nPoint = strData.indexOf(".");
		String strFront = new String(strData), strEnd = "";
		if (nPoint != -1) {
			strFront = strData.substring(0, nPoint);
			strEnd = strData.substring(nPoint + 1, strData.length());
		}
		String strTemp = "";
		// 小锟斤拷锟角帮拷锟斤拷锟斤拷菁锟�,"
		strTemp = new String(strFront);
		strFront = "";
		int i;
		for (i = strTemp.length() - 1; i >= 0; i--) {
			char cData;
			cData = strTemp.charAt(i);
			strFront = cData + strFront;
		}
		// 锟斤拷锟斤拷锟揭★拷锟轿�
		if (lSacle > 0) {
			strData = strFront + "." + strEnd;
		}
		// 锟斤拷锟斤拷
		else {
			strData = strFront;
		}
		if (dAmount < 0 && !strData.equals("0.00")) {
			strData = "-" + strData;
		}
		return strData;
	}

	/**
	 * 锟斤拷式锟斤拷锟叫憋拷慕锟斤拷,锟斤拷千锟街号ｏ拷锟斤拷锟轿拷锟斤拷蚍祷乜锟�
	 * 
	 * @author weiliao
	 * @param dAmount
	 *            锟斤拷锟�
	 * @param lSacle
	 *            jingdu
	 * @return
	 */
	public static String formatAmount3(double dAmount, int lSacle) {
		return (dAmount == 0.0) ? "" : MiscUtil.formatAmount(dAmount, lSacle);
	}

	/**
	 * 锟斤拷锟斤拷锟绞斤拷锟斤拷锟筋，锟斤拷","去锟斤拷
	 * 
	 * @param strData
	 *            锟斤拷锟�
	 */
	public static String reverseFormatAmount(String strData) {
		int i;
		String strTemp;
		// 去锟斤拷锟斤拷锟叫碉拷","
		strTemp = new String(strData);
		strData = "";
		for (i = 0; i < strTemp.length(); i++) {
			char cData;
			cData = strTemp.charAt(i);
			if (cData != ',') {
				strData = strData + cData;
			}
		}
		return strData;
	}

	/**
	 * 锟斤拷锟斤拷锟绞斤拷锟斤拷锟筋，锟斤拷锟斤拷锟�"," 锟斤拷","去锟斤拷 锟斤拷锟斤拷 double.
	 * 
	 * @param strData
	 *            锟斤拷锟�
	 */
	public static double reverseFormatAmountTodouble(String strData) {
		if (strData == null || "".equals(strData)) {
			return 0;
		}
		return Double.parseDouble(reverseFormatAmount(strData));
	}

	/** 小锟斤拷锟襟补筹拷锟睫讹拷值 */
	private static double getDouble(int iValue) {
		double dTempValue = 1;
		for (int i = 0; i < iValue; i++) {
			dTempValue = dTempValue / 10;
		}
		return dTempValue;
	}

	/**
	 * double锟斤拷锟斤拷锟斤拷莞锟绞斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷耄�
	 * 
	 * @param dValue
	 *            锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟街�
	 * @param lScale
	 *            锟斤拷锟斤拷 Method format.
	 * @param dValue
	 * @param lScale
	 * @return String
	 */
	public static double doubleRound(double dValue, int lScale) {
		double dValue1 = Math.abs(dValue);
		BigDecimal bd1 = new BigDecimal(Double.toString(dValue1));
		BigDecimal bd2 = new BigDecimal(Double.toString(getDouble(lScale + 4)));
		BigDecimal bd;
		bd = bd1.add(bd2);
		bd = bd.setScale(lScale, BigDecimal.ROUND_HALF_UP);
		if (dValue >= 0)
			return bd.doubleValue();
		else
			return bd.doubleValue() * -1;
	}

	/**
	 * double锟斤拷锟斤拷锟斤拷莞锟绞斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷耄�锟斤拷锟斤拷锟斤拷小锟斤拷锟街凤拷
	 * 
	 * @param dValue
	 *            锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟街�
	 * @param lScale
	 *            锟斤拷锟斤拷 Method format.
	 * @param dValue
	 * @param lScale
	 * @return String
	 */
	public static String stringFromdoubleRound(double dValue, int lScale) {
		DecimalFormat df = new DecimalFormat("#####0");
		double dValue1 = Math.abs(dValue);
		BigDecimal bd1 = new BigDecimal(Double.toString(dValue1));
		BigDecimal bd2 = new BigDecimal(Double.toString(getDouble(lScale + 4)));
		BigDecimal bd;
		bd = bd1.add(bd2);
		bd = bd.setScale(lScale, BigDecimal.ROUND_HALF_UP);
		double temp = 0;
		if (dValue >= 0)
			temp = bd.doubleValue();
		else
			temp = bd.doubleValue() * -1;
		return df.format(temp);
	}

	public static double doubleRound2(double dValue, int lScale) {
		java.math.BigDecimal bd;
		bd = new java.math.BigDecimal(Double.toString(dValue));
		bd = bd.setScale(lScale, java.math.BigDecimal.ROUND_UP);
		return bd.doubleValue();
	}

	/**
	 * 锟斤拷锟斤拷转锟斤拷为锟斤拷锟侥达拷写(只锟斤拷锟斤拷锟斤拷100锟斤拷锟斤拷)
	 * 
	 * @param iDigital
	 * @return
	 */
	public static String convertDigToCH(int iDigital) {
		String[] strArray = { "一", "锟斤拷", "锟斤拷", "锟斤拷", "锟斤拷", "锟斤拷", "锟斤拷", "锟斤拷",
				"锟斤拷", "十" };
		String strReturn = "";
		if (iDigital <= 10) {
			strReturn = "锟斤拷" + strArray[iDigital - 1] + "锟斤拷";
		}
		if (iDigital > 10 && iDigital < 100) {
			if (iDigital % 10 == 0) {
				if (iDigital == 10) {
					strReturn = "锟斤拷十锟斤拷";
				} else {
					strReturn = "锟斤拷" + strArray[(iDigital / 10) - 1] + "十"
							+ "锟斤拷";
				}
			} else {
				if (iDigital / 10 < 2) {
					strReturn = "锟斤拷十" + strArray[(iDigital % 10) - 1] + "锟斤拷";
				} else {
					strReturn = "锟斤拷" + strArray[(iDigital / 10) - 1] + "十"
							+ strArray[iDigital % 10] + "锟斤拷";
				}
			}
		}
		return strReturn;
	}

	/**
	 * 锟斤拷锟节革拷式锟斤拷: 锟斤拷锟斤拷:2003-10-25转锟斤拷为 锟斤拷元2003锟斤拷10锟斤拷25锟斤拷
	 * 
	 * @param strDate
	 * @return
	 */
	public static String formatTime(String strDate) {
		if (strDate == null || "".equals(strDate)) {
			return "    锟斤拷  锟斤拷  锟斤拷";
		}
		strDate = strDate.length() >= 10 ? strDate.substring(0, 10) : strDate;
		ArrayList arrayList = new ArrayList();
		String strTemp = "";
		int iTemp = 0;
		StringTokenizer strToken = new StringTokenizer(strDate, "-");
		while (strToken.hasMoreElements()) {
			arrayList.add((String) strToken.nextElement());
		}
		try {
			strTemp += (String) arrayList.get(0) + "锟斤拷";
			iTemp = Integer.parseInt((String) arrayList.get(1));
			strTemp += iTemp + "锟斤拷";
			iTemp = Integer.parseInt((String) arrayList.get(2));
			strTemp += iTemp + "锟斤拷";
		} catch (IndexOutOfBoundsException ex) {
			System.out
					.println("++++++++++++++++++++++++IndexOutOfBoundsException: strDate is "
							+ strDate);
			System.out
					.println("++++++++++++++++++++++++ and return     锟斤拷  锟斤拷  锟斤拷");
		} catch (Exception e) {
			strTemp = "   锟斤拷  锟斤拷  锟斤拷";
			e.printStackTrace();
		}
		return strTemp;
	}

	public static String formatAmount(Double dvalue, int scale) {
		if (dvalue == null)
			return "";
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(dvalue);
	}

	public static void main(String[] args) throws Exception {
		args = new String[2];
		int wdnum = MiscUtil.getWeekNum(MiscUtil.formatDate(new Date(), 2));
		if (wdnum == 1)
			wdnum = 7;
		Date bgdate = getBeforeDay(formatDate(new Date(), 1), 7 - 7 - 2 + wdnum);
		args[0] = MiscUtil.formatDate(bgdate, 1);
		args[1] = MiscUtil.formatDate(MiscUtil.getAfterDate(MiscUtil
				.getDateByString(args[0], 1), 6), 1);
		System.out.println(args[0] + "," + args[1]);

		Double ss = new Double(1.23456789E8);
		String cc = formatAmount(ss, 2);
		System.out.println(1111111);
	}
}
