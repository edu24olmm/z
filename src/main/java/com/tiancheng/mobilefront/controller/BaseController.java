package com.tiancheng.mobilefront.controller;

import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiancheng.mobilefront.bean.InfoS;
import com.tiancheng.mobilefront.bean.PhoneInfo;
import com.tiancheng.mobilefront.service.ApkInfoServiceImpl;
import com.tiancheng.mobilefront.utils.DateUtil;

@Controller
public class BaseController {
	private static final Logger logger = Logger.getLogger(BaseController.class);

	ApkInfoServiceImpl apkInfoService = new ApkInfoServiceImpl();

	/**
	 * 1 先根据时间段取当前的任务数量， 2根据任务数量来返回是否新增和留存 3
	 * 
	 * @param flagShua
	 *            刷留存0，刷新增1, 刷新增注册2 , 到量 3,刷注册留存 4
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/keepByServer.do")
	@ResponseBody
	public String keepByServer(String id) {

		// 2点-5点停止放量
		Calendar c = Calendar.getInstance();
		int h = c.get(Calendar.HOUR_OF_DAY);

//		if (h > 1 && h < 6) {
//			logger.info("2点-5点停止放量。。。。。。。。。。");
//			JSONObject topJ = new JSONObject();
//			topJ.put("flagShua", "3");
//			return topJ.toString();
//		}

		// 先判断做一日的留存，否则会出现 剩余大量留存的情况
		JSONObject oneDayJ = apkInfoService.keepByServer(id, 1);
		if (oneDayJ != null) {
			return oneDayJ.toString();
		}

		// 0代表，中止留存，转而做新增量
		int oldInt = DateUtil.getCurrentNumByTaskId("OLD", id);
		// 为0时， 还要判断是否达到了当天的设置的最大值

		if (0 == oldInt) {
			JSONObject topJ = new JSONObject();
			String isTF = apkInfoService.isReachTopOfEveryDay(id);
			if ("true".equalsIgnoreCase(isTF)) {
				topJ.put("flagShua", "3");
			} else {
				// 新增分2种情况
				topJ = apkInfoService.newOrRegister(id);
			}
			return topJ.toString();
		}

		// 优先做3,7,14日,15日,28,29,30,31
		// 留存开始----------------------------------------------------------------------------------
		JSONObject twoTO3J = apkInfoService.keepByServer(id, 3);
		if (twoTO3J != null) {
			return twoTO3J.toString();
		}
		JSONObject twoTO7J = apkInfoService.keepByServer(id, 7);
		if (twoTO7J != null) {
			return twoTO7J.toString();
		}
		JSONObject twoTO14J = apkInfoService.keepByServer(id, 14);
		if (twoTO14J != null) {
			return twoTO14J.toString();
		}
		JSONObject twoTO28J = apkInfoService.keepByServer(id, 28);
		if (twoTO28J != null) {
			return twoTO28J.toString();
		}
		JSONObject twoTO29J = apkInfoService.keepByServer(id, 29);
		if (twoTO29J != null) {
			return twoTO29J.toString();
		}
		JSONObject twoTO30J = apkInfoService.keepByServer(id, 30);
		if (twoTO30J != null) {
			return twoTO30J.toString();
		}
		JSONObject twoTO31J = apkInfoService.keepByServer(id, 31);
		if (twoTO31J != null) {
			return twoTO31J.toString();
		}
		JSONObject twoTO15J = apkInfoService.keepByServer(id, 15);
		if (twoTO15J != null) {
			return twoTO15J.toString();
		}
		// 做14日留存结束----------------------------------------------------------------------------------

		// 刷留存0，刷新增1, 刷新增注册2 , 到量 3,刷注册留存 4
		for (int i = 2; i < 32; i++) {

			if (i != 3 && i != 7 && i != 14 && i != 15 && i != 28 && i != 29 && i != 30 && i != 31) {
				JSONObject twoTOJ = new JSONObject();
				twoTOJ = apkInfoService.keepByServer(id, i);
				if (twoTOJ != null) {
					return twoTOJ.toString();
				}
			}

			if (i == 31) {
				JSONObject n31J = new JSONObject();
				String isTF = apkInfoService.isReachTopOfEveryDay(id);
				if ("true".equalsIgnoreCase(isTF)) {
					n31J.put("flagShua", "3");
				} else {
					// 判断注册
					n31J = apkInfoService.newOrRegister(id);
				}
				return n31J.toString();
			}
		}
		return null;
	}

	@RequestMapping(value = "/SaveToServer.do")
	@ResponseBody
	public String SaveToServer(String phoneInfo) {
		try {
			return apkInfoService.SaveToServer(phoneInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping(value = "/updateKeepPhone.do")
	@ResponseBody
	public String updateKeepPhone(String imei, String phoneNum) {
		try {
			apkInfoService.updateKeepPhone(imei, phoneNum);
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping(value = "/get599PhoneCode.do")
	@ResponseBody
	public String getPhoneCode(String phoneNum) {
		try {
			String code = apkInfoService.get599PhoneCode(phoneNum);
			return code;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping(value = "/getPhoneNum.do")
	@ResponseBody
	public String getPhoneNum(String registIdValue) {
		try {

			if (StringUtils.isEmpty(registIdValue) || "null".equalsIgnoreCase(registIdValue)) {
				registIdValue = "0";
			}

			return apkInfoService.getPhoneNum(registIdValue);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 从数据库获取手机信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryAndroidInfosByTaskId.do")
	@ResponseBody
	public String queryAndroidInfosByTaskId(String taskId) {

		try {
			PhoneInfo pi = apkInfoService.queryAndroidInfosByTaskId(taskId);
			JSONObject jo = JSONObject.fromObject(pi);
			logger.info(jo.toString());
			System.out.println(jo.toString());
			return jo.toString();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 返回一个名字
	 * 
	 * @return
	 */
	@RequestMapping(value = "/returnBackName.do", method = RequestMethod.POST)
	@ResponseBody
	public String returnBackName(HttpServletResponse response) {

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		try {
			InfoS i = apkInfoService.queryInfosById();
			JSONObject jo = JSONObject.fromObject(i);
			logger.info(jo.toString());
			return jo.toString();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 从数据库获取注册的用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryInfosById.do", method = RequestMethod.GET)
	@ResponseBody
	public void queryInfosById(String id_, HttpServletRequest request, HttpServletResponse response) {

		try {

			id_ = "1";

			InfoS i = apkInfoService.queryInfosById();
			logger.info(i.getNAME_());
			String data = i.getNAME_();

			OutputStream stream = response.getOutputStream();// 获取一个向Response对象写入数据的流,当tomcat服务器进行响应的时候，会将Response中的数据写给浏览器

			stream.write(data.getBytes("UTF-8"));

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}