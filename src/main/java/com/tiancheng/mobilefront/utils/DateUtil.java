package com.tiancheng.mobilefront.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DateUtil {
	private static final Logger logger = Logger.getLogger(DateUtil.class);
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	public static Map<String, String> mapc = new HashMap<String, String>();

	/**
	 * 根据id和是否是新增和留存,获取当前的任务数量
	 * 
	 * @param sign
	 * @param taskId
	 * @return
	 */
	public static int getCurrentNumByTaskId(String sign, String taskId) {

		try {
			// 先判断是否都消费完了
			if (!map.containsKey(sign + taskId)) {
				checkWorkTime(taskId);
			}

			int num = map.get(sign + taskId);

			int oldInt = map.get("OLD" + taskId);
			// int newInt = map.get("NEW" + taskId);

			// if (oldInt == 0 && newInt == 0) {
			if (oldInt == 0) {
				// System.out.println();
				// logger.info(taskId + ",num,init.......");
				checkWorkTime(taskId);
				num = 0;
			} else {
				decreaseNumByTaskId(sign, taskId);
			}

			return num;
		} catch (Exception e) {
			logger.debug("getCurrentNumByTaskId", e);
			return 0;
		}
	}

	/**
	 * 减法
	 * 
	 * @param sign
	 * @param taskId
	 */
	private static void decreaseNumByTaskId(String sign, String taskId) {
		try {
			int num = map.get(sign + taskId);
			if (num <= 0) {
				map.put(sign + taskId, 0);
			} else {
				map.put(sign + taskId, num - 1);
			}
		} catch (Exception e) {
			logger.debug("decreaseNumByTaskId:" + sign + taskId, e);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.print(i + ",OLD=" + getCurrentNumByTaskId("OLD", 123 + "") + "   ");
			System.out.println(i + ",OLD=" + getCurrentNumByTaskId("OLD", 456 + "") + "   ");
		}

	}

	public static int DaysBetween(java.util.Date infoDate, java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(infoDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static String today() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String today = format.format(new Date());
		return today;
	}

	public static int Days() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		java.util.Date startday = new Date();
		try {
			startday = format.parse("20150101");
		} catch (Exception e) {
		}
		java.util.Date today = new Date();
		return DaysBetween(startday, today); // ����ʱ+1
	}

	public static String Now() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String nowStr = dateFormat.format(now);
		return nowStr;
	}

	/**
	 * 通过时间段控制波动曲线
	 */
	private static void checkWorkTime(String taskId) {
		Calendar currentTime = Calendar.getInstance();

		Calendar fixTimea1 = Calendar.getInstance();
		fixTimea1.set(Calendar.HOUR_OF_DAY, 1);
		fixTimea1.set(Calendar.MINUTE, 00);

		Calendar fixTimea2 = Calendar.getInstance();
		fixTimea2.set(Calendar.HOUR_OF_DAY, 2);
		fixTimea2.set(Calendar.MINUTE, 00);

		Calendar fixTimea3 = Calendar.getInstance();
		fixTimea3.set(Calendar.HOUR_OF_DAY, 3);
		fixTimea3.set(Calendar.MINUTE, 00);

		Calendar fixTimea4 = Calendar.getInstance();
		fixTimea4.set(Calendar.HOUR_OF_DAY, 4);
		fixTimea4.set(Calendar.MINUTE, 00);

		Calendar fixTimeb5 = Calendar.getInstance();
		fixTimeb5.set(Calendar.HOUR_OF_DAY, 5);
		fixTimeb5.set(Calendar.MINUTE, 00);

		Calendar fixTimeb6 = Calendar.getInstance();
		fixTimeb6.set(Calendar.HOUR_OF_DAY, 6);
		fixTimeb6.set(Calendar.MINUTE, 00);

		Calendar fixTimeb7 = Calendar.getInstance();
		fixTimeb7.set(Calendar.HOUR_OF_DAY, 7);
		fixTimeb7.set(Calendar.MINUTE, 00);

		Calendar fixTimeb8 = Calendar.getInstance();
		fixTimeb8.set(Calendar.HOUR_OF_DAY, 8);
		fixTimeb8.set(Calendar.MINUTE, 00);

		Calendar fixTimeb9 = Calendar.getInstance();
		fixTimeb9.set(Calendar.HOUR_OF_DAY, 9);
		fixTimeb9.set(Calendar.MINUTE, 00);

		Calendar fixTimeb10 = Calendar.getInstance();
		fixTimeb10.set(Calendar.HOUR_OF_DAY, 10);
		fixTimeb10.set(Calendar.MINUTE, 00);

		Calendar fixTimeb11 = Calendar.getInstance();
		fixTimeb11.set(Calendar.HOUR_OF_DAY, 11);
		fixTimeb11.set(Calendar.MINUTE, 00);

		Calendar fixTimeb12 = Calendar.getInstance();
		fixTimeb12.set(Calendar.HOUR_OF_DAY, 12);
		fixTimeb12.set(Calendar.MINUTE, 00);

		Calendar fixTimeb13 = Calendar.getInstance();
		fixTimeb13.set(Calendar.HOUR_OF_DAY, 13);
		fixTimeb13.set(Calendar.MINUTE, 00);

		Calendar fixTimeb14 = Calendar.getInstance();
		fixTimeb14.set(Calendar.HOUR_OF_DAY, 14);
		fixTimeb14.set(Calendar.MINUTE, 00);

		Calendar fixTimeb15 = Calendar.getInstance();
		fixTimeb15.set(Calendar.HOUR_OF_DAY, 15);
		fixTimeb15.set(Calendar.MINUTE, 00);

		Calendar fixTimea16 = Calendar.getInstance();
		fixTimea16.set(Calendar.HOUR_OF_DAY, 16);
		fixTimea16.set(Calendar.MINUTE, 00);

		Calendar fixTimeb17 = Calendar.getInstance();
		fixTimeb17.set(Calendar.HOUR_OF_DAY, 17);
		fixTimeb17.set(Calendar.MINUTE, 00);

		Calendar fixTimea18 = Calendar.getInstance();
		fixTimea18.set(Calendar.HOUR_OF_DAY, 18);
		fixTimea18.set(Calendar.MINUTE, 00);

		Calendar fixTimea19 = Calendar.getInstance();
		fixTimea19.set(Calendar.HOUR_OF_DAY, 19);
		fixTimea19.set(Calendar.MINUTE, 00);

		Calendar fixTimea20 = Calendar.getInstance();
		fixTimea20.set(Calendar.HOUR_OF_DAY, 20);
		fixTimea20.set(Calendar.MINUTE, 00);

		Calendar fixTimea21 = Calendar.getInstance();
		fixTimea21.set(Calendar.HOUR_OF_DAY, 21);
		fixTimea21.set(Calendar.MINUTE, 00);

		Calendar fixTimea22 = Calendar.getInstance();
		fixTimea22.set(Calendar.HOUR_OF_DAY, 22);
		fixTimea22.set(Calendar.MINUTE, 00);

		Calendar fixTimea23 = Calendar.getInstance();
		fixTimea23.set(Calendar.HOUR_OF_DAY, 23);
		fixTimea23.set(Calendar.MINUTE, 00);

		Calendar fixTimeb24 = Calendar.getInstance();
		fixTimeb24.set(Calendar.HOUR_OF_DAY, 24);
		fixTimeb24.set(Calendar.MINUTE, 00);

		if (currentTime.after(fixTimea1) && currentTime.before(fixTimea2)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 35);
		} else if (currentTime.after(fixTimea2) && currentTime.before(fixTimea3)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 40);
		} else if (currentTime.after(fixTimea3) && currentTime.before(fixTimea4)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 32);
		} else if (currentTime.after(fixTimea4) && currentTime.before(fixTimeb5)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 40);
		} else if (currentTime.after(fixTimeb5) && currentTime.before(fixTimeb6)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 30);
		} else if (currentTime.after(fixTimeb6) && currentTime.before(fixTimeb7)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 20);
		} else if (currentTime.after(fixTimeb7) && currentTime.before(fixTimeb8)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 24);
		} else if (currentTime.after(fixTimeb8) && currentTime.before(fixTimeb9)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 24);
		} else if (currentTime.after(fixTimeb9) && currentTime.before(fixTimeb10)) {
			// map.put("NEW" + taskId, 2);
			map.put("OLD" + taskId, 28);
		} else if (currentTime.after(fixTimeb10) && currentTime.before(fixTimeb11)) {
			// map.put("NEW" + taskId, 5);
			map.put("OLD" + taskId, 8);
		} else if (currentTime.after(fixTimeb11) && currentTime.before(fixTimeb12)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 24);
		} else if (currentTime.after(fixTimeb12) && currentTime.before(fixTimeb13)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 32);
		} else if (currentTime.after(fixTimeb13) && currentTime.before(fixTimeb14)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 20);
		} else if (currentTime.after(fixTimeb14) && currentTime.before(fixTimeb15)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 24);
		} else if (currentTime.after(fixTimeb15) && currentTime.before(fixTimea16)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 20);
		} else if (currentTime.after(fixTimea16) && currentTime.before(fixTimeb17)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 30);
		} else if (currentTime.after(fixTimeb17) && currentTime.before(fixTimea18)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 20);
		} else if (currentTime.after(fixTimea18) && currentTime.before(fixTimea19)) {
			// map.put("NEW" + taskId, 2);
			map.put("OLD" + taskId, 28);
		} else if (currentTime.after(fixTimea19) && currentTime.before(fixTimea20)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 8);
		} else if (currentTime.after(fixTimea20) && currentTime.before(fixTimea21)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 22);
		} else if (currentTime.after(fixTimea21) && currentTime.before(fixTimea22)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 18);
		} else if (currentTime.after(fixTimea22) && currentTime.before(fixTimea23)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 30);
		} else if (currentTime.after(fixTimea23) && currentTime.before(fixTimeb24)) {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 33);
		} else {
			// map.put("NEW" + taskId, 1);
			map.put("OLD" + taskId, 14);
		}
	}
}
