package com.tiancheng.mobilefront.job;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ibatis.dao.client.DaoManager;
import com.tiancheng.mobilefront.bean.BaseBean;
import com.tiancheng.mobilefront.bean.TaskInfo;
import com.tiancheng.mobilefront.dao.ApkInfoDAO;
import com.tiancheng.mobilefront.service.ApkInfoServiceImpl;
import com.tiancheng.mobilefront.service.DaoConfig;
import com.tiancheng.mobilefront.utils.DateUtil;
import com.tiancheng.mobilefront.utils.SendMail;

/**
 * 每隔1个小时检查任务是否在正常工作
 * 
 * @author Administrator
 *
 */
public class CheckTasks implements Job {
	private static final Logger logger = Logger.getLogger(CheckTasks.class);
	private DaoManager daoManager;
	private ApkInfoDAO apkInfoDAO;
	private Map<String, String> mapc = DateUtil.mapc;

	ApkInfoServiceImpl as = new ApkInfoServiceImpl();

	public CheckTasks() {
		daoManager = DaoConfig.getDaoManager();
		apkInfoDAO = (ApkInfoDAO) daoManager.getDao(ApkInfoDAO.class);
	}

	/**
	 * 1 先检查是否到量 2
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("begin checkTasks...");

		List<TaskInfo> obj = apkInfoDAO.getTaskList();

		// int d = DateUtil.Days();

		String sendMessageStr = "";//发送字符串的消息 

		for (TaskInfo t : obj) {

			String trachTopB = as.isReachTopOfEveryDay(t.getId_() + "");

			if ("false".equalsIgnoreCase(trachTopB)) {// 没有到量的任务进行检查
				Double countKeepD = 0D;
				for (int i = 1; i < 32; i++) {
					countKeepD += as.countKeep(t.getId_() + "", i);
				}

				if (countKeepD > 0) {// 还有留存的处理情况
					String mapV = mapc.get(t.getId_() + "LC");

					if (!StringUtils.isEmpty(mapV)) {// map里面没有值的情况
						if (Double.parseDouble(mapV) - countKeepD <= 3) {
							// send email
							logger.info("send email");
							sendMessageStr += t.getTaskName() + ",OLD STOP！";
						} else {
							// do nothing....
						}
					} else {
						// do nothing....
					}
					mapc.put(t.getId_() + "LC", countKeepD + "");
				} else {// 留存做完的处理情况

					int day = DateUtil.Days();
					BaseBean bb = new BaseBean();
					bb.setTaskId(t.getId_());
					bb.setDay(day);
					Double obj2StrD = Double.parseDouble(apkInfoDAO.getKeep(bb));

					String mapV = mapc.get(t.getId_() + "XZ");
					if (!StringUtils.isEmpty(mapV)) {// map里面没有值的情况

						if (0D == obj2StrD) {
							continue;
						}

						if (obj2StrD - Double.parseDouble(mapV) <= 3) {
							// send email
							logger.info("send email");
							sendMessageStr += t.getTaskName() + ",NEW STOP！";
						} else {
							// do nothing....
						}
					} else {
						// do nothing....
					}
					mapc.put(t.getId_() + "XZ", obj2StrD + "");
				}

			}

		}

		SendMail.sendMessageServer(sendMessageStr, "");

	}
}
