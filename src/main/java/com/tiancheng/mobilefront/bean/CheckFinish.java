package com.tiancheng.mobilefront.bean;

import java.util.Date;

public class CheckFinish {
	private static final long serialVersionUID = 528822298787822064L;

	private int taskId;
	private int keepSum;
	private int todaySum;
	private Date lastCountTime;
	private int meanCount;
	private String finishHour;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getKeepSum() {
		return keepSum;
	}

	public void setKeepSum(int keepSum) {
		this.keepSum = keepSum;
	}

	public int getTodaySum() {
		return todaySum;
	}

	public void setTodaySum(int todaySum) {
		this.todaySum = todaySum;
	}

	public Date getLastCountTime() {
		return lastCountTime;
	}

	public void setLastCountTime(Date lastCountTime) {
		this.lastCountTime = lastCountTime;
	}

	public int getMeanCount() {
		return meanCount;
	}

	public void setMeanCount(int meanCount) {
		this.meanCount = meanCount;
	}

	public String getFinishHour() {
		return finishHour;
	}

	public void setFinishHour(String finishHour) {
		this.finishHour = finishHour;
	}

}