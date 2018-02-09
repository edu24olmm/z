package com.tiancheng.mobilefront.utils;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author Administrator
 *
 */
public class DXNumCode extends BasePhoneNumCode {
	private static final Logger logger = Logger.getLogger(DXNumCode.class);
	public static ArrayList<String> numAl = new ArrayList<String>();

	public static String f02Token = "";
	public static String f02BaseUrl = "http://dx1.mtmo.com.cn:5002/qma/ValidateCodeSvl";
	// static String f02UserName = "xixihaha";
	// static String f02Pwd = "qwer1234";
	public static String f02UserName = "42951LJ";
	public static String f02Pwd = "jc8545";
	/**
	 * 项目id
	 */
	static String pid = "553";// 雅活荟

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

		// String phoneNum = get599PhoneNum();

		// String phoneNum = "13410611679";
		// Map<String, String> c = new HashMap<String, String>();
		// c.put("123", null);

	}

	/**
	 * 返回手机号码
	 */
	public static String get599PhoneNum(String registIdValue) {
		get599Token();
		
		if(!"0".equals(registIdValue)){
			pid=registIdValue;
		}
		
		String phonenum = sendGet(f02BaseUrl, "action=getMobilenum&" + "token=" + f02Token + "&uid=" + f02UserName + "&pid=" + pid + "&Code=UTF8", "");
		// String phonenum = "13715243751";
		logger.info("phonenum=" + phonenum);
		try {
			if (StringUtils.isEmpty(phonenum)) {
				return "手机号为空";
			}
			phonenum = phonenum.split("\\|")[0];

			numAl.add(phonenum);

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
	public static String get599Token() {
		if (StringUtils.isEmpty(f02Token)) {
			f02Token = sendGet(DXNumCode.f02BaseUrl, "action=loginIn&" + "uid=" + f02UserName + "&pwd=" + f02Pwd, "");
			f02Token = f02Token.split("\\|")[1];
			DXNumCodeMyThreadDX myThread = new DXNumCodeMyThreadDX();
			Thread t1 = new Thread(myThread);
			t1.start();
		}
		return f02Token;
	}
}

class DXNumCodeMyThreadDX extends BasePhoneNumCode implements Runnable {
	private static final Logger logger = Logger.getLogger(DXNumCodeMyThreadDX.class);

	@Override
	public void run() {
		// 获取验证码并不再使用本号
		String code = "";
		// int i = 10;
		while (true) {
			try {
				Thread.sleep(5500);

				ArrayList<String> al = DXNumCode.numAl;

				if (al.size() == 0) {
					logger.info("MyThreadDX没有号码");
					Thread.sleep(1000);
				}

				for (String phoneNum : al) {
					if (phoneNum.startsWith("1")) {
						code = sendGet(DXNumCode.f02BaseUrl, "action=getVcodeAndReleaseMobile&" + "uid=" + DXNumCode.f02UserName + "&token=" + DXNumCode.f02Token + "&mobile=" + phoneNum, "");

						if (code.contains("\\|") && code.split("\\|").length > 1) {
							code = code.split("\\|")[1];
							logger.info("DXNumCode里的==phoneNum=" + phoneNum + "phoneCode=" + code);
//							codeMap.put(phoneNum, code);
							al.remove(phoneNum);
						} else {
							logger.info("code不正常.....=" + code);
						}

					} else {
						logger.info("号码无效=" + phoneNum);
					}
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
