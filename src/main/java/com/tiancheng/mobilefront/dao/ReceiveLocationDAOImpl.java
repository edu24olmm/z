package com.tiancheng.mobilefront.dao;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.tiancheng.mobilefront.bean.InfoS;
import com.tiancheng.mobilefront.bean.Location;

public class ReceiveLocationDAOImpl extends SqlMapDaoTemplate implements ReceiveLocationDAO {

	public ReceiveLocationDAOImpl(DaoManager daoManager) {
		super(daoManager);
	}

	@Override
	public String saveLocation(Location l) {
		this.insert("queryFiles.saveLocation", l);
		return "";
	}

	@Override
	public Location queryLocationByImei(Location lo) {
		Location l = (Location) this.queryForObject("queryFiles.queryLocationByImei", lo);
		return l;
	}

	@Override
	public void insertIntoInfos(InfoS i) {
		this.insert("queryFiles.insertIntoInfos", i);
	}

}