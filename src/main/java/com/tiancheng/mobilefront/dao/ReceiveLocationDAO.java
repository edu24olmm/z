package com.tiancheng.mobilefront.dao;

import com.tiancheng.mobilefront.bean.InfoS;
import com.tiancheng.mobilefront.bean.Location;

public interface ReceiveLocationDAO {

	public String saveLocation(Location l);

	public Location queryLocationByImei(Location lo);

	public void insertIntoInfos(InfoS i);
}
