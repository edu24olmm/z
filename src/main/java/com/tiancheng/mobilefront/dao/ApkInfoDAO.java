package com.tiancheng.mobilefront.dao;

import java.util.List;

import com.tiancheng.mobilefront.bean.BaseBean;
import com.tiancheng.mobilefront.bean.CheckFinish;
import com.tiancheng.mobilefront.bean.InfoS;
import com.tiancheng.mobilefront.bean.Order;
import com.tiancheng.mobilefront.bean.Pages;
import com.tiancheng.mobilefront.bean.PhoneInfo;
import com.tiancheng.mobilefront.bean.TaskInfo;

public interface ApkInfoDAO {

	void updateKeep(Long id, String keepDays);

	String isReachTopOfEveryDay(int d, int taskId);

	String isReachTopOfEveryDayOfRegist(int d, int taskId);

	String keepByServerUnExecute(String keepD, int keep2, String id);

	Object keepByServerExecute(int keep2, String id, String d);

	String saveToServerValidate(String valueOf);

	void SaveToServer(PhoneInfo pi);

	PhoneInfo keepByServer(int keep2, String id, String d);

	List<TaskInfo> getTaskList();

	void updateTask(TaskInfo t);

	void addTask(TaskInfo t);

	void deleteTask(String id);

	List<TaskInfo> getTaskByLimit(Pages p);

	String getKeep(BaseBean bb);

	String getHistoryStr(int id_);

	String getKeepNum(BaseBean bb);

	String executeKeep(BaseBean bb);

	void updateHistoryStr(TaskInfo t_tmp);

	void deleteKeep(String id_);

	boolean isNeedRegister(String id);

	void updateTaskOffSetAll();

	void updateKeepPhone(String imei, String phoneNum);

	TaskInfo getKeepsByTaskId(String bb);

	String queryAllRegisters(String taskId);

	void addSellement(Order torder);

	PhoneInfo queryAndroidInfosByTaskId(String taskId);

	InfoS queryInfosById(String id_);

	CheckFinish queryCheckFinish(int id_);

	void insertCheckFinish(CheckFinish cfin);

	void updateCheckFinish(CheckFinish cfup);
}
