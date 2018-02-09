package com.tiancheng.mobilefront.utils;

import com.tiancheng.mobilefront.bean.InfoS;
import com.tiancheng.mobilefront.service.ReceiveLocationServiceImpl;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 * 
 */
public class DownPersonInfos {

	public static void main(String[] args) {

		String info = sendGet("http://www.gzsij.com/sfz.aspx", "", "");

		writeToFile(info);

	}

	public void insert() {

		ReceiveLocationServiceImpl ri = new ReceiveLocationServiceImpl();
		while (true) {

			String info = sendGet("http://www.gzsij.com/sfz.aspx", "", "");

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String strs[] = info.split(",");

			InfoS i = new InfoS();
			i.setNAME_(strs[0]);
			i.setSFZ_(strs[1]);
			i.setYB_(strs[2]);
			i.setADDRES_(strs[3]);
			i.setEMAIL_(strs[4]);
			i.setPHONENUM(strs[5]);
			ri.insertIntoInfos(i);

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 
	 */
	public static void writeToFile(String content) {
		content += "\r";
		String path = "d:/";
		String fileName = "info";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			fileName = fileName + ".txt";
			BufferedWriter fos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + fileName, true)));
			fos.write(content);
			fos.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String sendGet(String url, String param, String session) {
		String result = "";
		BufferedReader in = null;
		String cookieVal = null;
		String key = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 閹垫挸绱戦崪瀛禦L娑斿妫块惃鍕箾閹猴拷
			URLConnection connection = realUrl.openConnection();
			// 鐠佸墽鐤嗛柅姘辨暏閻ㄥ嫯顕Ч鍌氱潣閹拷
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			if (!param.contains("loginIn")) {
				connection.setRequestProperty("Cookie", session);
			}
			// 瀵よ櫣鐝涚�鐐烘閻ㄥ嫯绻涢幒锟�
			connection.connect();
			// 閼惧嘲褰囬幍锟芥箒閸濆秴绨叉径鏉戠摟濞堬拷
			Map<String, List<String>> map = connection.getHeaderFields();
			// 闁秴宸婚幍锟芥箒閻ㄥ嫬鎼锋惔鏂裤仈鐎涙顔�
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

			// 鐎规矮绠�BufferedReader鏉堟挸鍙嗗ù浣规降鐠囪褰嘦RL閻ㄥ嫬鎼锋惔锟�
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		// 娴ｈ法鏁inally閸ф娼甸崗鎶芥４鏉堟挸鍙嗗ù锟�
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
