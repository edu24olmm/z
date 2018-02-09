package com.tiancheng.mobilefront.bean;

public class BaseBean {
	Long id;
	String keepDays;
	int day;
	int taskId;
	String registerId;
	
	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeepDays() {
		return keepDays;
	}

	public void setKeepDays(String keepDays) {
		this.keepDays = keepDays;
	}
	

}
