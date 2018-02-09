package com.tiancheng.mobilefront.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiancheng.mobilefront.bean.Location;
import com.tiancheng.mobilefront.service.ReceiveLocationServiceImpl;
import com.tiancheng.mobilefront.utils.BasePhoneNumCode;

@Controller
public class ReceiveLocationController {
	private static final Logger logger = Logger.getLogger(ReceiveLocationController.class);

	ReceiveLocationServiceImpl rec = new ReceiveLocationServiceImpl();

	@RequestMapping(value = "/reLocation.do")
	@ResponseBody
	public String reLocation(String locationStr) {

		logger.info("locationStr=" + locationStr);
		JSONObject json = JSONObject.fromObject(locationStr);

		String lTimeStr = json.get("lTimeStr") + "";
		String provider = json.get("provider") + "";
		String altitude = json.get("altitude") + "";
		String latitude = json.get("latitude") + "";
		String longitude = json.get("longitude") + "";
		String speed = json.get("speed") + "";
		String phoneType = json.get("phoneType") + "";
		String action = json.get("action") + "";
		String isScreenOn = json.get("isScreenOn") + "";

		Location location = new Location();
		location.setTime(lTimeStr);
		location.setProvider(provider);
		location.setAltitude(altitude);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setSpeed(speed);
		location.setPhoneType(phoneType);
		location.setAction(action);
		location.setIsScreenOn(isScreenOn);

		String resStr = "";
		try {
			resStr = rec.saveLocation(location);
		} catch (Exception e) {
			logger.error(e);
		}

		return resStr;
	}

	@RequestMapping(value = "/smap.do")
	public String showBaiduMap(HttpServletRequest request, HttpServletResponse response, String imei, String phoneType) throws IOException {
		// String location =
		// "http://map.baidu.com/?latlng=38.062966,114.550372&title=cc&content=loc&output=html&src=dddd";
		Location lo = new Location();
		lo.setImei(imei);
		lo.setPhoneType(phoneType);
		Location resLo = rec.queryLocationByImei(lo);

		String changeurl = "http://api.map.baidu.com/geoconv/v1/?coords="+resLo.getLongitude()+","+resLo.getLatitude()+"&from=1&to=5&ak=zqB3gCV5ajNqxQlYjRbhC2DY&output=json";
		String changeRes = BasePhoneNumCode.sendGet(changeurl, "", "");
		JSONObject changeJson = JSONObject.fromObject(changeRes);
		JSONArray jsonArray = changeJson.getJSONArray("result");
		JSONObject latLong = jsonArray.getJSONObject(0);
		
		StringBuffer loStr = new StringBuffer();
		loStr.append("bdapp://map/marker?location=");
		loStr.append(latLong.get("y"));
		loStr.append(",");
		loStr.append(latLong.get("x"));
		loStr.append("&title=我的位置&content=我的位置&src=LQ_C");

		logger.info(loStr.toString());

		// String location =
		// "bdapp://map/marker?location=40.047669,116.313082&title=我的位置&content=百度奎科大厦&src=yourCompanyName|yourAppName";
		// response.sendRedirect(location);
		// response.sendRedirect(loStr.toString());

		request.setAttribute("phoneType", resLo.getPhoneType());
		request.setAttribute("isScreenOn", resLo.getIsScreenOn());
		request.setAttribute("time", resLo.getTime());
		request.setAttribute("loStr", loStr.toString());
		return "jsp/map/smap";
	}
}
