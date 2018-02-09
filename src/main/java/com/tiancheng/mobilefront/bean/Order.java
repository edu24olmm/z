package com.tiancheng.mobilefront.bean;

public class Order {

	public String ID_;
	public String taskName;
	public String beginDate;
	public String endDate;
	public int num;
	public Double money;
	public Double price;
	public String remark;
	public String registers;
	public String days;
	public int isSellement;
	
	
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getID_() {
		return ID_;
	}
	public void setID_(String iD_) {
		ID_ = iD_;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRegisters() {
		return registers;
	}
	public void setRegisters(String registers) {
		this.registers = registers;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public int getIsSellement() {
		return isSellement;
	}
	public void setIsSellement(int isSellement) {
		this.isSellement = isSellement;
	}
	
	
}
