package com.jd.quan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jd.utils.Base64Quan;

/**
 * f02获取验证码
 * 
 * @author Administrator
 *
 */
public class BaiTiao {
	private static final Logger logger = Logger.getLogger(BaiTiao.class);

	public static void main(String[] args) {
		String f02BaseUrl = "http://mkt.baitiao.jd.com/activity/participatemore";
		String identify = Base64Quan.getBase64("G7HA");
		String time = System.currentTimeMillis() + "";
		String params = "callback=jQuery183024732075817883015_1446904428011&key=66176c918cea403d9c59fac68c3bcf61&sourceCode=JDPC&identify=" + identify + "&_=" + time + "";

		String session = "lighting=28EE8E54608F958A5E784CB2047B017D6FDFCB2D02531251570D4FE590EB2709A37C5C7896F3C8090848E216D00BB3C15B2C718F2C0DBAB41CB60E731757B91FC3D0E5658CB72E5AE525131DA03C12BDCE487BA7C7CFDD6A0C89D36086CFA9602DBDB7F76D14C7992C72C85FE6A14374E9702B5BA65A6347ECF0B55DF6605046BDE973D71BA4683464C0734A277C0DB7; mt_subsite=||1111%2C1444046846; cn=7; dmpjs=dmp-d28177c0835042af9ed177e12039f9a96a06ec; _tp=iV7L6p9%2F1NmZ7DrXQ8hcwA%3D%3D; _pst=edu24olmm; TrackID=1D0juP2rh59P7TEQxvTXDvGkDFkBTWtxzVEv4NmYy-h-XZkZxEOPDo6LlZI8U4p0u; pinId=QQxWrYvU8mzHsDqJQzoSug; unick=jd_edu2; pin=edu24olmm; thor=8FF2083EB3BC355B53F2378A8AFB171BDF807E1FD7958767D71752DBB61190622F0192E69E979FC7A803F2A30739EC39362605319D0659AFBE568A4F414FF2FB910D1D6083FBE14B10D52E0734FC4756890E763C554192CFADDC5053D0D9AEF84DA6AA711B4EF24B152E64A584B118AE8C675CD676C5CEA0555E284771AE9E41B931B139ED96D41571EA84460994D496; __jda=122270672.1281140393.1429710791.1446872448.1446903661.57; __jdb=122270672.14.1281140393|57.1446903661; __jdc=122270672; __jdv=122270672|dmp|dmp_108|cpc|dmp_108_69512_d28177c0835042af9ed177e12039f9a96a06ec_1446872435; __jdu=1281140393";
		System.out.println(sendGetCookies(f02BaseUrl, params, session));

	}

	/**
	 * 初始化token
	 */
	// static {
	// f02Token = sendGet(f02BaseUrl, params, "");
	// if (StringUtils.isNotEmpty(f02Token)) {
	// f02Token = f02Token.split("\\|")[1];
	// }
	// System.out.println("f02Token..." + f02Token);
	// }

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
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
		return result;
	}

	public static String sendGetCookies(String url, String param, String session) {
		String result = "";
		BufferedReader in = null;
		String cookieVal = null;
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
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
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
