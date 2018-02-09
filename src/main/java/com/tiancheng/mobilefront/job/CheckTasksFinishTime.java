package com.tiancheng.mobilefront.job;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONObject;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ibatis.dao.client.DaoManager;
import com.tiancheng.mobilefront.bean.BaseBean;
import com.tiancheng.mobilefront.bean.CheckFinish;
import com.tiancheng.mobilefront.bean.Pages;
import com.tiancheng.mobilefront.bean.TaskInfo;
import com.tiancheng.mobilefront.dao.ApkInfoDAO;
import com.tiancheng.mobilefront.service.ApkInfoServiceImpl;
import com.tiancheng.mobilefront.service.DaoConfig;
import com.tiancheng.mobilefront.utils.DateUtil;
import com.tiancheng.mobilefront.utils.SendMail;

/**
 * 
 * @author Administrator
 *
 */
public class CheckTasksFinishTime implements Job {
	private static final Logger logger = Logger.getLogger(CheckTasksFinishTime.class);
	private DaoManager daoManager;
	private ApkInfoServiceImpl apkInfoService;
	private ApkInfoDAO apkInfoDAO;
	private Map<String, String> mapc = DateUtil.mapc;

	ApkInfoServiceImpl as = new ApkInfoServiceImpl();

	public CheckTasksFinishTime() {
		apkInfoService = new ApkInfoServiceImpl();
		daoManager = DaoConfig.getDaoManager();
		apkInfoDAO = (ApkInfoDAO) daoManager.getDao(ApkInfoDAO.class);
	}

	/**
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("begin CheckTasksFinishTime...");

		Pages p = new Pages();
		p.setStart(0);
		p.setEnd(20);
		try {
			List<TaskInfo> tList = apkInfoDAO.getTaskByLimit(p);
			for (TaskInfo ti : tList) {

				int day = DateUtil.Days();

				String info = "";

				BaseBean bb = new BaseBean();
				bb.setTaskId(ti.getId_());
				bb.setDay(day);

				// 今日新增
				String obj2Str = apkInfoDAO.getKeep(bb);
				int obj2Strint = Integer.valueOf(obj2Str);

				int countKeep = 0;
				for (int i = 1; i < 32; i++) {
					countKeep += apkInfoService.countKeep(ti.getId_() + "", i);
				}

				CheckFinish cf = apkInfoDAO.queryCheckFinish(ti.getId_());
				if (cf == null) {
					CheckFinish cfin = new CheckFinish();
					cfin.setTaskId(ti.getId_());
					cfin.setFinishHour("");
					cfin.setKeepSum(countKeep);
					cfin.setLastCountTime(new Date());
					cfin.setMeanCount(0);
					cfin.setTodaySum(obj2Strint);
					apkInfoDAO.insertCheckFinish(cfin);
				} else {
					CheckFinish cfup = new CheckFinish();
					cfup.setTaskId(ti.getId_());
					cfup.setKeepSum(countKeep);
					cfup.setLastCountTime(new Date());
					int res = obj2Strint - cf.getTodaySum() + cf.getKeepSum() - countKeep;
					cfup.setMeanCount(res);
					Calendar c = Calendar.getInstance();
					c.get(Calendar.HOUR_OF_DAY);
					c.get(Calendar.MINUTE);
					if (res == 0) {
						cfup.setFinishHour(0 + "");
					} else {
						int finishInt = (Integer.valueOf(ti.getTopOfEveryDay()) - obj2Strint + countKeep) / res / 6;
						cfup.setFinishHour(finishInt + "");
					}
					cfup.setTodaySum(obj2Strint);
					apkInfoDAO.updateCheckFinish(cfup);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
}
