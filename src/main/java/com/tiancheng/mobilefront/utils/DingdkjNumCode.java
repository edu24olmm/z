package com.tiancheng.mobilefront.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * f02获取验证码
 * 
 * @author Administrator
 *
 */
public class DingdkjNumCode extends BasePhoneNumCode {
	private static final Logger logger = Logger.getLogger(DingdkjNumCode.class);

	public static String f02Token = "";
	public static String f02BaseUrl = "http://www.yayayama.com:19876";
	// static String f02UserName = "xixihaha";
	// static String f02Pwd = "qwer1234";
	public static String f02UserName = "edu24olmm";
	public static String f02Pwd = "4536331";
	/**
	 * 项目id
	 */
	static String pid = "33860";// 雅活荟

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
		//
		// while (true) {
		// for (Map.Entry<String, String> entry : codeMap.entrySet()) {
		// System.out.println("Key = " + entry.getKey() + ", Value = " +
		// entry.getValue());
		// try {
		// Thread.sleep(999999);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// }
		// }
		// }

	}
	
	/**
	 * GET - http://xapi.yika66.com/User/getMessage?token=登陆token&ItemId=项目ID&Phone=获取的号码
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static String get599PhoneCode(String phoneNum) {
		String code = sendGet(f02BaseUrl + "/User/getMessage", "token=" + f02Token + "&ItemId="+pid+"Code=UTF8&Phone=" + phoneNum, "");
		logger.info("phonenum=" + phoneNum+",code="+code);
		return code;
	}

	/**
	 * 返回手机号码
	 * 
	 * @param registIdValue
	 */
	public static String get599PhoneNum(String registIdValue) {
		get599Token();

		if (!"0".equals(registIdValue)) {
			pid = registIdValue;
		}

		String phonenum = sendGet(f02BaseUrl + "/Url/userGetPhone", "token=" + f02Token + "&Code=UTF8&ItemId=" + pid + "&Code=UTF8", "");
		// String phonenum = "13715243751";
		logger.info("phonenum=" + phonenum);
		try {
			if (StringUtils.isEmpty(phonenum)) {
				return "手机号为空";
			}
			phonenum = phonenum.substring(0, phonenum.length() - 1);
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
			f02Token = sendGet(DingdkjNumCode.f02BaseUrl + "/Url/userLogin", "uName=" + f02UserName + "&pWord=" + f02Pwd + "&Code=UTF8", "");
			f02Token = f02Token.split("\\&")[0];

//			MyThread myThread = new MyThread();
//			Thread t1 = new Thread(myThread);
//			t1.start();

		}
		return f02Token;
	}

}

/**
 * 获取验证码开启线程
 * 
 * @author Administrator
 *
 */
//class MyThread extends BasePhoneNumCode implements Runnable {
//	private static final Logger logger = Logger.getLogger(MyThread.class);

//	@Override
//	public void run() {
//		try {
//			// 获取验证码并不再使用本号
//			String code = "";
//			// int i = 10;
//			while (true) {
//				Thread.sleep(5000);
//				code = sendGet(DingdkjNumCode.f02BaseUrl + "/Url/getMsgQueue", "token=" + DingdkjNumCode.f02Token + "&Code=UTF8", "");
//
//				logger.info("原始队列里的内容为：" + code);
//
//				if (code.contains("Session")) {
//					String f02Token = sendGet(DingdkjNumCode.f02BaseUrl + "/Url/userLogin", "uName=" + DingdkjNumCode.f02UserName + "&pWord=" + DingdkjNumCode.f02Pwd + "&Code=UTF8", "");
//					f02Token = f02Token.split("\\&")[0];
//					DingdkjNumCode.f02Token = f02Token;
//					Thread.sleep(6000);
//				}
//
//				// i--;
//				// if (i == 0) {
//				// System.out.println("对不起这次没有获取到验证码!!");
//				// }
//				if (code.contains("验证码")) {
//
//					String[] size = code.split("[end]");
//
//					for (String str : size) {
//						if (str.contains("验证码")) {
//							String phoneNum = str.split("&")[2];
//							System.out.println(phoneNum);
//							String phoneCode = str.split("验证码")[1].substring(1, 7);
//							System.out.println(phoneCode);
//							logger.info("DingdkjNumCode里的内容为,phoneNum=" + phoneNum + "phoneCode=" + phoneCode);
//							DingdkjNumCode.codeMap.put(phoneNum, phoneCode);
//							Thread.sleep(1000);
//						}
//					}
//
//					// String phoneNum = code.split("&")[2];
//					// String phoneCode = code.split("验证码")[1].substring(1, 7);
//
//				}
//				logger.info("codeMap里面的内容为");
//
//				for (Map.Entry<String, String> entry : DingdkjNumCode.codeMap.entrySet()) {
//					logger.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//				}
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}