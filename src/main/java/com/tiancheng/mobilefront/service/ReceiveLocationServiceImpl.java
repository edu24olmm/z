package com.tiancheng.mobilefront.service;

import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoManager;
import com.tiancheng.mobilefront.bean.InfoS;
import com.tiancheng.mobilefront.bean.Location;
import com.tiancheng.mobilefront.dao.ReceiveLocationDAO;

public class ReceiveLocationServiceImpl implements ReceiveLocationService {
	private DaoManager daoManager;
	private ReceiveLocationDAO receiveLocationDAO;

	private static final Logger logger = Logger.getLogger(ReceiveLocationServiceImpl.class);

	public ReceiveLocationServiceImpl() {
		daoManager = DaoConfig.getDaoManager();
		receiveLocationDAO = (ReceiveLocationDAO) daoManager.getDao(ReceiveLocationDAO.class);
	}

	@Override
	public String saveLocation(Location l) {
		return receiveLocationDAO.saveLocation(l);
	}

	@Override
	public Location queryLocationByImei(Location lo) {
		// TODO Auto-generated method stub
		return receiveLocationDAO.queryLocationByImei(lo);
	}

	@Override
	public void insertIntoInfos(InfoS i) {
		// TODO Auto-generated method stub
		receiveLocationDAO.insertIntoInfos(i);
	}

}
