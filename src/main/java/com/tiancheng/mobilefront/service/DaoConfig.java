package com.tiancheng.mobilefront.service;

import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;

public class DaoConfig {
	private static final Logger logger = Logger.getLogger(DaoConfig.class);
	private static final String resource = "dao.xml";
	private static final DaoManager daoManager = newDaoManager(null);

	public static DaoManager getDaoManager() {
		return daoManager;
	}

	public static DaoManager newDaoManager(Properties props) {
		Resources.setCharset(Charset.forName("UTF-8"));
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			return DaoManagerBuilder.buildDaoManager(reader, props);
		} catch (Throwable e) {
			logger.error("DaoConfig happen Exception", e);
			throw new RuntimeException("Could not initialize DaoConfig.  Cause: " + e, e);
		}
	}

}
