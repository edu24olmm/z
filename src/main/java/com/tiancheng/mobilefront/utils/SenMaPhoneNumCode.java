package com.tiancheng.mobilefront.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author Administrator
 *
 */
public class SenMaPhoneNumCode extends BasePhoneNumCode {
	private static final Logger logger = Logger.getLogger(SenMaPhoneNumCode.class);

	static String f02Token = "";
	static String f02BaseUrl = "http://api.iyylw.com";
	static String f02UserName = "edu24olmm";
	static String f02Pwd = "4536331";
	/**
	 * 项目id
	 */
	static String pid = "993";

	public static void main(String[] args) {
		// String baseUrl = "http://42.120.60.152/do.aspx";
		// String loginParam = "action=loginIn&uid=edu24olmm&pwd=4536331";
		// String tbParam = "action=getMobilenum&type=3";

		// String session = sendGet(baseUrl, loginParam, null);
		// System.out.println(session);
		// System.out.println(sendGet(baseUrl, tbParam, session));

		// System.out.println(sendGet(f02BaseUrl, p, ""));
		// System.out.println(sendGet(f02BaseUrl, "action=getUserInfos&uid=" +
		// f02UserName + "&token=" + f02Token, ""));
		//
		// String phoneNum = getPhoneNum();
		// String phoneCode = getPhoneCode(phoneNum);
		//
		// System.out.println(phoneNum);
		// System.out.println(phoneCode);
		//
		// // getRecvingInfo
		// System.out.println(sendGet(f02BaseUrl, "action=getRecvingInfo&uid=" +
		// f02UserName + "&token=" + f02Token + "&pid=" + pid, ""));

		String num = getPhoneNum();
		
		System.out.println(num);

		getPhoneCode(num);
	}

	/**
	 * 获取验证码
	 * 
	 * @param phonenum
	 * @return
	 */
	private static String getPhoneCode(String phonenum) {
		if (StringUtils.isNotEmpty(phonenum)) {
			try {
				// 获取验证码并不再使用本号
				String code = "";
				int i = 30;
				while (true) {
					Thread.sleep(5000);
					code = sendGet(f02BaseUrl + "/get_sms", "token=" + f02Token + "&pid=" + pid + "&mobile=" + phonenum, "");
					i--;
					if (code == "") {
						return "";
					}
					if (phonenum.contentEquals(code)) {
						return code;
					}
					if (i == 0) {
						return "";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * 返回手机号码
	 */
	private static String getPhoneNum() {

		getToken();

		String phonenum = sendGet(f02BaseUrl + "/get_mobile", "&token=" + f02Token + "&pid=" + pid, "");
		// String phonenum = "13715243751";
		try {
			phonenum = phonenum.split(",")[0].split(":")[1].replace("\"", "");
		} catch (Exception e) {
			phonenum = "";//
			e.printStackTrace();
			return "";
		}
		return phonenum;
	}

	/**
	 * 初始化token
	 */
	static void getToken() {
		String params = "user=" + f02UserName + "&pwd=" + f02Pwd;
		f02Token = sendGet(f02BaseUrl + "/login", params, "");
		if (StringUtils.isNotEmpty(f02Token)) {
			f02Token = f02Token.split(",")[0].split(":")[1].replace("\"", "");
		}
		System.out.println("f02Token=" + f02Token);
	}

}
