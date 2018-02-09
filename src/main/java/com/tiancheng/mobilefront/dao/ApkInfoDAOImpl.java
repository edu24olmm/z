package com.tiancheng.mobilefront.dao;

import java.util.List;
import java.util.Random;

import org.apache.axis.utils.StringUtils;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.tiancheng.mobilefront.bean.BaseBean;
import com.tiancheng.mobilefront.bean.CheckFinish;
import com.tiancheng.mobilefront.bean.InfoS;
import com.tiancheng.mobilefront.bean.Order;
import com.tiancheng.mobilefront.bean.Pages;
import com.tiancheng.mobilefront.bean.PhoneInfo;
import com.tiancheng.mobilefront.bean.TaskInfo;
import com.tiancheng.mobilefront.utils.DateUtil;

public class ApkInfoDAOImpl extends SqlMapDaoTemplate implements ApkInfoDAO {

	public ApkInfoDAOImpl(DaoManager daoManager) {
		super(daoManager);
	}

	@Override
	public void updateKeep(Long id, String keepDays) {
		BaseBean bb = new BaseBean();
		bb.setId(id);
		bb.setKeepDays(keepDays);
		this.update("queryFiles.updateKeep", bb);

	}

	@Override
	public void updateKeepPhone(String imei, String phoneNum) {
		BaseBean bb = new BaseBean();
		bb.setKeepDays(imei);
		bb.setRegisterId(phoneNum);
		this.update("queryFiles.updateKeepPhone", bb);

	}

	@Override
	public String isReachTopOfEveryDay(int day, int taskId) {
		BaseBean bb = new BaseBean();
		bb.setDay(day);
		bb.setTaskId(taskId);
		return String.valueOf(this.queryForObject("queryFiles.isReachTopOfEveryDay", bb));
	}

	/**
	 * 判断是否达到注册的量了
	 * 
	 * @param day
	 * @param taskId
	 * @return
	 */
	@Override
	public String isReachTopOfEveryDayOfRegist(int day, int taskId) {
		BaseBean bb = new BaseBean();
		bb.setDay(day);
		bb.setTaskId(taskId);
		return String.valueOf(this.queryForObject("queryFiles.isReachTopOfEveryDayOfRegist", bb));
	}

	@Override
	public String keepByServerUnExecute(String keepD, int keep2, String id) {
		BaseBean bb = new BaseBean();
		bb.setDay(keep2);
		// bb.setKeepDays(keepD);
		bb.setId(Long.parseLong(id + ""));
		String str = String.valueOf(this.queryForObject("queryFiles.keepByServerUnExecute" + keepD, bb));
		if ("null".equalsIgnoreCase(str)) {
			str = "0";
		}
		return str;
	}

	@Override
	public Object keepByServerExecute(int keep2, String id, String KeepDays) {
		BaseBean bb = new BaseBean();
		bb.setDay(keep2);
		bb.setKeepDays(KeepDays);
		bb.setId(Long.parseLong(id + ""));
		return this.queryForObject("queryFiles.keepByServerExecute", bb);
	}

	@Override
	public String saveToServerValidate(String taskId) {
		BaseBean bb = new BaseBean();
		bb.setId(Long.parseLong(taskId));
		return String.valueOf(this.queryForObject("queryFiles.saveToServerValidate", bb));
	}

	@Override
	public void SaveToServer(PhoneInfo pi) {
		this.insert("queryFiles.SaveToServer", pi);
	}

	@Override
	public PhoneInfo keepByServer(int keep2, String id, String d) {

		BaseBean bb = new BaseBean();
		bb.setDay(keep2);
		bb.setId(Long.parseLong(id));
		bb.setKeepDays(d);

		return (PhoneInfo) this.queryForObject("queryFiles.keepByServer", bb);
	}

	@Override
	public List<TaskInfo> getTaskList() {
		return this.queryForList("queryFiles.getTaskList");
	}

	@Override
	public void updateTask(TaskInfo t) {
		this.update("queryFiles.updateTask", t);

	}

	/**
	 * 创建任务
	 * 
	 * @param t
	 */
	@Override
	public void addTask(TaskInfo t) {
		Integer min = (Integer) this.queryForObject("queryFiles.selectMinPDModel");
		t.setPdmId(min);
		this.insert("queryFiles.addTask", t);
	}

	@Override
	public void deleteTask(String id) {
		// TODO Auto-generated method stub
		this.delete("queryFiles.deleteTask", id);
		this.delete("queryFiles.deleteKeep", id);
	}

	@Override
	public List<TaskInfo> getTaskByLimit(Pages p) {
		// TODO Auto-generated method stub
		return this.queryForList("queryFiles.getTaskByLimit", p);
	}

	@Override
	public String getKeep(BaseBean bb) {

		return this.queryForObject("queryFiles.getKeep", bb) + "";
	}

	@Override
	public String getHistoryStr(int id_) {
		String res = this.queryForObject("queryFiles.getHistoryStr", id_) + "";
		if ("null".equalsIgnoreCase(res)) {
			res = "";
		}
		return res;
	}

	@Override
	public String getKeepNum(BaseBean bb) {
		return this.queryForObject("queryFiles.getKeepNum", bb) + "";
	}

	@Override
	public String executeKeep(BaseBean bb) {
		return this.queryForObject("queryFiles.executeKeep", bb) + "";
	}

	@Override
	public void updateHistoryStr(TaskInfo t_tmp) {
		this.update("queryFiles.updateHistoryStr", t_tmp);
	}

	@Override
	public void deleteKeep(String id) {
		// TODO Auto-generated method stub
		this.delete("queryFiles.deleteKeep", id);
		TaskInfo ti = new TaskInfo();
		ti.setId_(Integer.valueOf(id));
		ti.setHistoryStr("");
		this.update("queryFiles.updateHistoryStr", ti);
	}

	/**
	 * 判断是否需要注册
	 */
	@Override
	public boolean isNeedRegister(String taskId) {
		String getRegisterRate = this.queryForObject("queryFiles.getRegisterRate", taskId) + "";

		if (StringUtils.isEmpty(getRegisterRate) || "null".equalsIgnoreCase(getRegisterRate)) {
			return false;
		}

		Double getRegisterRateInt = Double.valueOf(getRegisterRate);
		if (getRegisterRateInt <= 0) {
			return false;
		}

		int d = DateUtil.Days();
		BaseBean bb = new BaseBean();
		bb.setDay(d);
		bb.setTaskId(Integer.valueOf(taskId));

		// 查询目前需要注册的量
		String queryNeedRegisters = this.queryForObject("queryFiles.queryNeedRegisters", bb) + "";

		// 查询已经注册的量
		String queryRegisters = this.queryForObject("queryFiles.queryRegisters", bb) + "";

		Double queryNeedRegistersInt = Double.valueOf(queryNeedRegisters);

		Double queryRegistersInt = Double.valueOf(queryRegisters);

		// 需要注册的量为0，返回需要注册标记
		if (queryNeedRegistersInt == 0) {
			return true;
		}

		if (queryNeedRegistersInt - queryRegistersInt > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateTaskOffSetAll() {
		int number = new Random().nextInt(25) + 1;
		this.update("queryFiles.updateTaskOffSetAll", number);
	}

	@Override
	public String queryAllRegisters(String taskId) {
		BaseBean bb = new BaseBean();
		bb.setTaskId(Integer.valueOf(taskId));
		String queryRegisters = this.queryForObject("queryFiles.queryAllRegisters", bb) + "";
		return queryRegisters;
	}

	@Override
	public TaskInfo getKeepsByTaskId(String id_) {
		BaseBean bb = new BaseBean();
		bb.setId(Long.parseLong(id_));

		return (TaskInfo) this.queryForObject("queryFiles.getKeepsByTaskId", bb);
	}

	@Override
	public void addSellement(Order torder) {
		this.insert("queryFiles.addSellement", torder);
	}

	/**
	 * 根据任务号，查询下一个手机信息
	 * 
	 * @param taskId
	 * @return
	 */
	@Override
	public PhoneInfo queryAndroidInfosByTaskId(String taskId) {
		PhoneInfo pi = new PhoneInfo();
		pi = (PhoneInfo) this.queryForObject("queryFiles.queryAndroidInfosByTaskId", taskId);//查询下一个手机信息
		TaskInfo ti = new TaskInfo();
		ti.setId_(Integer.valueOf(taskId));
		this.update("queryFiles.updateTaskPdmId", ti);//把下个手机信息的id，写入task表中，
		return pi;
	}

	@Override
	public InfoS queryInfosById(String id_) {
		// TODO Auto-generated method stub
		return (InfoS) this.queryForObject("queryFiles.queryInfosById",id_);
	}

	@Override
	public CheckFinish queryCheckFinish(int id_) {
		// TODO Auto-generated method stub
		return (CheckFinish)this.queryForObject("queryFiles.queryCheckFinish",id_);
	}

	@Override
	public void insertCheckFinish(CheckFinish cfin) {
		this.insert("queryFiles.insertCheckFinish", cfin);
	}

	@Override
	public void updateCheckFinish(CheckFinish cfup) {
		this.update("queryFiles.updateCheckFinish",cfup);
	}

}