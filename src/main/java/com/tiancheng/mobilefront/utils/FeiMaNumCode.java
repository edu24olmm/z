package com.tiancheng.mobilefront.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * f02获取验证码
 * 
 * @author Administrator
 *
 */
public class FeiMaNumCode extends BasePhoneNumCode {
	private static final Logger logger = Logger.getLogger(FeiMaNumCode.class);

	public static String f02Token = "";
	// public static String f02BaseUrl = "http://xapi.83r.com";
	public static String f02BaseUrl = "http://xapi.yika66.com";
	// static String f02UserName = "xixihaha";
	// static String f02Pwd = "qwer1234";
	public static String f02UserName = "edu24olmm";
	public static String f02Pwd = "4536331";

	/**
	 * 项目id
	 */
	static String pid = "";// 雅活荟

	/**
	 * 项目id
	 */
	// static String pid = "17693";// 智慧树

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

		// String phoneNum = get599PhoneNum("0");
		// System.out.println(phoneNum);

		get599Token();
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

		Pattern p = Pattern.compile("\\d{6,}");//
		String code = "MSG&33860&14719855056&【唯饭】验证码106760，您正在注册成为唯饭App用户，感谢您的支持！[End]RES&33860&14719855056[End]";
		code = code.split("验证码")[1];
		Matcher m = p.matcher(code);
		if (m.find()) {
			code = m.group();
			System.out.println(code);
		}

	}

	/**
	 * GET -
	 * http://xapi.yika66.com/User/getMessage?token=登陆token&ItemId=项目ID&Phone
	 * =获取的号码
	 * 
	 * @param phoneNum
	 * @return
	 */
	public static String get599PhoneCode(String phoneNum) {

		logger.info("get599PhoneCodepid为"+pid);

		String code = sendGet(f02BaseUrl + "/User/getMessage", "token=" + f02Token + "&ItemId=" + pid + "&Code=UTF8&Phone=" + phoneNum, "");
		logger.info("phonenum,====" + phoneNum + ",code=" + code);
		if (code.contains("QQ")) {
			return "";
		} else {
			String[] yzmArr = code.split("初始密码为");
			if (yzmArr.length > 1) {
				return yzmArr[1];
			} else {
				return "";
			}
		}
	}

	/**
	 * 返回手机号码
	 */
	public static String get599PhoneNum(String pid) {
		get599Token();
		String phonenum = sendGet(f02BaseUrl + "/User/getPhone", "token=" + f02Token + "&ItemId=" + pid + "&code=UTF8&PhoneType=0", "");
		FeiMaNumCode.pid=pid;
		logger.info("pid设置成功"+pid);
		logger.info("phonenum=" + phonenum);
		try {
			if (StringUtils.isEmpty(phonenum)) {
				return "手机号为空";
			}

			if (phonenum.contains("Session")) {
				logger.info("获取session失效，重新获取session。。。。。。");
				f02Token = "";
				get599Token();
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
			f02Token = sendGet(FeiMaNumCode.f02BaseUrl + "/User/login", "uName=" + f02UserName + "&pWord=" + f02Pwd + "&code=UTF8&Developer=AW5lxtpEptz5TjQre4BM%2bg%3d%3d", "");
			f02Token = f02Token.split("\\&")[0];

			// FeiMaMyThread myThread = new FeiMaMyThread();
			// Thread t1 = new Thread(myThread);
			// t1.start();

		}
		sendGet(" http://xapi.yika66.com/User/ReleaseAllPhone?token="+f02Token,"","");
		logger.info("token全部释放，，，，，，，，，，，，，，，");
		return f02Token;
	}

}

/**
 * 获取验证码开启线程
 * 
 * @author Administrator
 *
 */
// class FeiMaMyThread extends BasePhoneNumCode implements Runnable {
// private static final Logger logger = Logger.getLogger(FeiMaMyThread.class);
//
// @Override
// public void run() {
// try {
// // 获取验证码并不再使用本号
// String code = "";
// // int i = 10;
// while (true) {
// Thread.sleep(5000);
// code = sendGet(FeiMaNumCode.f02BaseUrl + "/User/getMessage", "token=" +
// FeiMaNumCode.f02Token + "&code=UTF8", "");
//
// logger.info("原始队列里的内容为：" + code);
//
// if (code.contains("Session")) {
// String f02Token = sendGet(FeiMaNumCode.f02BaseUrl + "/Url/userLogin",
// "uName=" + FeiMaNumCode.f02UserName + "&pWord=" + FeiMaNumCode.f02Pwd +
// "&code=UTF8", "");
// f02Token = f02Token.split("\\&")[0];
// FeiMaNumCode.f02Token = f02Token;
// Thread.sleep(6000);
// }
//
// // i--;
// // if (i == 0) {
// // System.out.println("对不起这次没有获取到验证码!!");
// // }
// if (code.contains("验证码")) {
//
// String[] size = code.split("[end]");
//
// for (String str : size) {
// if (str.contains("验证码")) {
// String phoneNum = str.split("&")[2];
// System.out.println(phoneNum);
// String phoneCode = str.split("验证码")[1].substring(1, 7);
// System.out.println(phoneCode);
// logger.info("DingdkjNumCode里的内容为,phoneNum=" + phoneNum + "phoneCode=" +
// phoneCode);
// // codeMap.put(phoneNum, phoneCode);
// Thread.sleep(1000);
// }
// }
//
// // String phoneNum = code.split("&")[2];
// // String phoneCode = code.split("验证码")[1].substring(1, 7);
//
// }
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// }