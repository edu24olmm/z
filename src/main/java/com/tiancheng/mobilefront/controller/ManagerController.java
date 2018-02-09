package com.tiancheng.mobilefront.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiancheng.mobilefront.bean.TaskInfo;
import com.tiancheng.mobilefront.service.ApkInfoServiceImpl;

@Controller
public class ManagerController {
	private static final Logger logger = Logger.getLogger(ManagerController.class);

	ApkInfoServiceImpl apkInfoService = new ApkInfoServiceImpl();

	@RequestMapping(value = "/getKeepList.do")
	@ResponseBody
	public JSONObject getKeepList(int rows, int page) {
		Map map = new HashMap();
		System.out.println(rows + page);
		int start = (page - 1) * rows;
		int end = page * rows;

		List<TaskInfo> taskList = apkInfoService.getKeepList(start, end);

		int total = apkInfoService.getTaskList().size();

		JSONObject jo = new JSONObject();
		jo.put("total", total);
		jo.put("rows", taskList);

		// map.put("total", total);
		// map.put("rows", taskList);
		return jo;
	}

	@RequestMapping(value = "/toMain.do")
	public String toMain() {
		return "jsp/main/ghhychina.main";
	}

	@RequestMapping(value = "/toTaskList.do")
	public String toTaskList() {
		return "jsp/agent/agents";
	}

	@RequestMapping(value = "/toSelect.do")
	public String toSelect() {
		return "/jsp/agent/selectInfo";
	}

	@RequestMapping(value = "/updateTask.do")
	public String updateTask(TaskInfo t) {

		apkInfoService.updateTask(t);

		return "修改成功";
	}

	@RequestMapping(value = "/deleteKeep.do")
	@ResponseBody
	public String deleteKeep(String id_) {

		apkInfoService.deleteKeep(id_);

		return "修改成功";
	}

	@RequestMapping(value = "/addSellement.do")
	@ResponseBody
	public String addSellement(String id_, String taskName, String price) {

		apkInfoService.addSellement(id_, taskName, price);

		return "修改成功";
	}

	@RequestMapping(value = "/deleteTask.do")
	@ResponseBody
	public String deleteTask(String id_) {

		apkInfoService.deleteTask(id_);

		return "修改成功";
	}

	@RequestMapping(value = "/addTask.do")
	public String addTask(TaskInfo t) {

		apkInfoService.addTask(t);

		return "添加成功";
	}

	@RequestMapping(value = "/getTaskList.do")
	@ResponseBody
	public List<TaskInfo> getTaskList(HttpServletResponse response) {

		JSONObject json = new JSONObject();

		Map map = new HashMap();
		List<TaskInfo> taskList = apkInfoService.getTaskList();

		JSONArray ja = JSONArray.fromObject(taskList);

		map.put("rows", ja.toString());
		map.put("total", taskList.size());
		return taskList;
	}
}