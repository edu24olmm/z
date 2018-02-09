package com.tiancheng.mobilefront.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

/**
 * 
 * @author Administrator
 * 
 */
public class BasePhoneNumCode {
	// public static Map<String, String> codeMap = new ConcurrentHashMap<String,
	// String>();
	private static final Logger logger = Logger.getLogger(BasePhoneNumCode.class);

	public static void main(String[] args) {

	}

	// /**
	// * 从map里获取验证码____old
	// *
	// * @param phoneNum
	// * @return
	// */
	// public static String get599PhoneCode(String phoneNum) {
	// String code = codeMap.get(phoneNum);
	// try {
	// codeMap.remove(phoneNum);
	// } catch (Exception e) {
	// logger.error(e.toString());
	// }
	// return code;
	// }

	/**
	 * 
	 * @param phoneCode
	 */
	public static void writeToFile(String flag, String phoneCode, String fileName) {
		StringBuffer sb = new StringBuffer();
		if (!TextUtils.isEmpty(flag)) {
			sb.append(flag).append("|");
		}
		sb.append(phoneCode);

		String path = "/sdcard/Android/";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			fileName = fileName + ".txt";
			FileOutputStream fos = new FileOutputStream(path + fileName);
			fos.write(sb.toString().getBytes());
			fos.close();
		} catch (Exception e) {
		}
	}

	/**
	 * update to server
	 * 
	 * @param imei
	 * @param phoneNum
	 * @return
	 */
	public static String updateKeepPhone(String imei, String phoneNum) {
		String url = GetStartApkHttpService.URL + "updateKeepPhone.do";
		String param = "imei=" + imei + "&phoneNum=" + phoneNum;
		sendGet(url, param, "");
		return "";
	}

	/**
	 * 
	 * @param phoneCode
	 */
	public static void delFile(String fileName) {
		String path = "/sdcard/Android/";
		fileName = fileName + ".txt";
		File dir = new File(path + fileName);
		dir.deleteOnExit();
	}

	public static String sendGet(String url, String param, String session) {
		String result = "";
		BufferedReader in = null;
		String cookieVal = null;
		String key = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setConnectTimeout(120000);
			connection.setReadTimeout(120000);

			if (!param.contains("loginIn")) {
				connection.setRequestProperty("Cookie", session);
			}
			connection.connect();
			for (int i = 1; (key = connection.getHeaderFieldKey(i)) != null; i++) {
				if (key.equalsIgnoreCase("set-cookie")) {
					cookieVal = connection.getHeaderField(i);
					cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
				}
			}

			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
