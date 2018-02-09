package com.tiancheng.mobilefront.service;

import com.tiancheng.mobilefront.bean.InfoS;
import com.tiancheng.mobilefront.bean.Location;

public interface ReceiveLocationService {

	public String saveLocation(Location l);

	public Location queryLocationByImei(Location lo);
	
	public void insertIntoInfos(InfoS i);
}