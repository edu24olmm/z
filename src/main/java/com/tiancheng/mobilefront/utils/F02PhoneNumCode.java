package com.tiancheng.mobilefront.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * f02获取验证码
 * 
 * @author Administrator
 *
 */
public class F02PhoneNumCode {
	private static final Logger logger = Logger.getLogger(F02PhoneNumCode.class);

	static String f02Token = "";
	static String f02BaseUrl = "http://api.f02.cn/http.do";
	static String f02UserName = "edu24olmm";
	static String f02Pwd = "xiaoabao66";
	/**
	 * 项目id
	 */
	static String pid = "14370";

	static String params = "action=loginIn&uid=edu24olmm&pwd=xiaoabao66";

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

		String phoneNum = getPhoneNum();
		String phoneCode = getPhoneCode(phoneNum);

		System.out.println(phoneNum);
		System.out.println(phoneCode);

		// getRecvingInfo
		System.out.println(sendGet(f02BaseUrl, "action=getRecvingInfo&uid=" + f02UserName + "&token=" + f02Token + "&pid=" + pid, ""));

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
					code = getVcodeAndReleaseMobile(phonenum);
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
		String phonenum = sendGet(f02BaseUrl, "action=getMobilenum&uid=" + f02UserName + "&token=" + f02Token + "&pid=" + pid, "");
		// String phonenum = "13715243751";
		try {
			phonenum = phonenum.split("\\|")[0];
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
	static {
		f02Token = sendGet(f02BaseUrl, params, "");
		if (StringUtils.isNotEmpty(f02Token)) {
			f02Token = f02Token.split("\\|")[1];
		}
		System.out.println("f02Token..." + f02Token);
	}

	/**
	 * 获取验证码并不再使用本号
	 */
	private static String getVcodeAndReleaseMobile(String phonenum) {
		String code = sendGet(f02BaseUrl, "action=getVcodeAndReleaseMobile&uid=" + f02UserName + "&token=" + f02Token + "&mobile=" + phonenum, "");
		logger.debug("phonenum=" + phonenum + "-----------code=" + code);
		return code;
	}

	public static String sendGet(String url, String param, String session) {
		String result = "";
		BufferedReader in = null;
		String cookieVal = null;
		String key = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if (!param.contains("loginIn")) {
				connection.setRequestProperty("Cookie", session);
			}
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
				if (key.equalsIgnoreCase("set-cookie")) {
					cookieVal = connection.getHeaderField(i);
					cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
					// sessionId = sessionId + cookieVal + ";";
					// System.out.println(key + "--->" + map.get(key));
				}
			}

			// if (param.contains("loginIn")) {
			// return cookieVal + ";";
			// }

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		System.out.println("result===" + result);

		return result;
	}
}
