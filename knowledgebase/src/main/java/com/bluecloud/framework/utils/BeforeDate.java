package com.bluecloud.framework.utils;

/**
 * 
 * @author dafei
 *
 */
public class BeforeDate {
	private int thisYear = 0; //��
	private int thisMonth = 0; //��	
	private int thisDay = 0; //��	
	private int number = 0; //��������	

	//���췽��	
	public BeforeDate(String today, int number) {
		this.thisYear = Integer.parseInt(today.substring(0, 4));
		this.thisMonth = Integer.parseInt(today.substring(4, 6));
		this.thisDay = Integer.parseInt(today.substring(6, today.length()));
		this.number = number;
	}

	//�ж��Ƿ�Ϊ����	
	private boolean isLeapYear(int y) {
		boolean leapYear = false;
		if (y % 400 == 0) {
			leapYear = true;
		} else {
			if (y % 4 == 0 && y % 100 != 0) {
				leapYear = true;
			}
		}
		return leapYear;
	}

	//��ȡǰһ����	
	private int getBeforeMonth(int m) {
		int beforeMonth = 0;
		if (m > 1 && m <= 12) {
			beforeMonth = m - 1;
		} else if (m == 1) {
			thisYear = thisYear - 1;
			beforeMonth = 12;
		}
		return beforeMonth;
	}

	private int getDayNumber(int y, int m) {
		if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10
				|| m == 12) {
			return 31;
		} else if (m == 4 || m == 6 || m == 9 || m == 11) {
			return 30;
		} else if (m == 2) { // �ж��Ƿ�Ϊ����			
			if (isLeapYear(y)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	public void getBeforeDay() {
		if (number >= thisDay) {
			number = number - thisDay; //�������ڣ���ǰ����֮ǰ��������			
			thisDay = 1;
			for (;;) {
				thisMonth = getBeforeMonth(thisMonth);
				//��ȡ��ǰһ�����Ǽ���				
				int dayNumber = getDayNumber(thisYear, thisMonth);
				//��ȡǰһ���µġ�����				
				if (number < dayNumber) {
					//���ǰһ���µ��������ʣ�������������ڡ���ǰһ���¡�					
					thisDay = dayNumber - number;
					break;
				} else {
					//���ǰһ���µ�����С��ʣ�������������ڡ�����ǰһ���¡�					
					number = number - dayNumber;
				}
			}
		} else {
			thisDay = thisDay - number; //ֱ��ȡ������		
		}

	}

	public String getBeforeDayString() {
		getBeforeDay();
		String m = "" + thisMonth;
		String d = "" + thisDay;
		if (thisMonth < 10)
			m = "0" + thisMonth;
		if (thisDay < 10)
			d = "0" + thisDay;
		return "" + thisYear + m + d;
	}

	public static void main(String[] args) {
		String date = "20070430"; //����		
		int number = 100; //����		
		BeforeDate dboh = new BeforeDate(date, number);
		String str = dboh.getBeforeDayString();
		System.out.println("����Ϊ��" + str);
	}

}
