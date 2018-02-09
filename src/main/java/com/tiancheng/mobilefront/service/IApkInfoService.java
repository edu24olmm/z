package com.tiancheng.mobilefront.service;

import net.sf.json.JSONObject;

public abstract interface IApkInfoService {

	public String SaveToServer(String phoneInfo);

	public abstract JSONObject keepByServer(String id, int d);

	public abstract String isReachTopOfEveryDay(String id);

}