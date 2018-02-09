package com.tiancheng.mobilefront.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Administrator
 * 
 */
public class YiPianYunPhoneNumCode extends BasePhoneNumCode {

	static String f02Token = "";
	static String f02BaseUrl = "http://api.ypyun.com/http.do";
	// static String f02UserName = "xiaoduan";
	// static String f02Pwd = "qwer1234";
	static String f02UserName = "edu24olmm";
	static String f02Pwd = "4536331";
	/**
	 * 椤圭洰id
	 */
	static String pid = "6131";

	public static void main(String[] args) {

		getToken();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getToken();

		// String phoneNum = getPhoneNum();
		// executeBs(phoneNum);
		// String phoneCode = getPhoneCode(phoneNum);
		// System.out.println(phoneNum);
		// System.out.println(phoneCode);
		//
		// // writeToFile("4", phoneCode, "phoneCode");

		// String str = "15019443645;|20AB06B7E9F82F2FD8D4EB7D343E51F9";
		// str = str.replaceAll(";", "");
		// System.out.println(str);

		// getRecvingInfo
		// System.out.println(sendGet(f02BaseUrl, "action=getRecvingInfo&uid="
		// + f02UserName + "&token=" + f02Token + "&pid=" + pid, ""));

	}

	/**
	 * 
	 * @param phonenum
	 * @return
	 */
	public static String getPhoneCode(String phonenum) {

		if (!TextUtils.isEmpty(phonenum)) {
			try {
				Thread.sleep(1000);
				String code = "";
				int i = 30;

				executeBs(phonenum);

				while (true) {
					Thread.sleep(5000);

					code = getExeResult(phonenum);

					i--;

					if (i == 0) {
						return "";
					}

					// int len = code.split("\\|").length;
					// if (len > 2) {
					// code = code.split("\\|")[1];
					// Pattern p = Pattern.compile("\\d{6,}");//
					// // 这个2是指连续数字的最少个数
					// Matcher m = p.matcher(code);
					// if (m.find()) {
					// code = m.group();
					// code = code.substring(0, 4);
					// System.out.println("phonecode:" + code);
					// }
					// return code;
					// }

					if (!TextUtils.isEmpty(code)) {
						Pattern p = Pattern.compile("\\d{6,}");//
						// 这个2是指连续数字的最少个数
						Matcher m = p.matcher(code);
						if (m.find()) {
							code = m.group();
							System.out.println("phonecode:" + code);
							return code;
						}
						System.out.println(code);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	private static String getExeResult(String phonenum) {
		String getExeResult = sendGet(f02BaseUrl, "action=getExeResult&uid=" + f02UserName + "&token=" + f02Token + "&mobile=" + phonenum + "&step=1&pid=" + pid, "");
		return getExeResult;
	}

	/**
	 */
	public static String getPhoneNum() {

		getToken();

		String phonenum = sendGet(f02BaseUrl, "action=getMobilenum&uid=" + f02UserName + "&token=" + f02Token + "&pid=" + pid, "");

		if (phonenum.contains(";")) {
			phonenum = phonenum.replaceAll("\\;", "");
		}

		// String phonenum = "13715243751";
		try {
			int strLen = phonenum.split("\\|").length;
			if (strLen != 2) {
				return phonenum;
			}
			phonenum = phonenum.split("\\|")[0];
		} catch (Exception e) {
			phonenum = "";//
			e.printStackTrace();
			return "";
		}
		return phonenum;
	}

	/**
	 */
	private static void getToken() {
		f02Token = sendGet(f02BaseUrl, "action=loginIn&uid=" + f02UserName + "&pwd=" + f02Pwd, "");
		if (!TextUtils.isEmpty(f02Token)) {
			f02Token = f02Token.split("\\|")[1];
		}
		System.out.println("f02Token..." + f02Token);
	}

	/**
	 * executeBs
	 */
	private static String executeBs(String phonenum) {
		// http://api.ypyun.com/http.do?action=executeBs&uid=用户名&token=令牌&pid=项目ID&mobile=手机号码&step=x
		String executeBs = sendGet(f02BaseUrl, "action=executeBs&uid=" + f02UserName + "&token=" + f02Token + "&mobile=" + phonenum + "&step=1&pid=" + pid, "");
		return executeBs;
	}

}
