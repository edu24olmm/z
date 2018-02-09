package com.tiancheng.mobilefront.job;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ibatis.dao.client.DaoManager;
import com.tiancheng.mobilefront.bean.BaseBean;
import com.tiancheng.mobilefront.bean.TaskInfo;
import com.tiancheng.mobilefront.dao.ApkInfoDAO;
import com.tiancheng.mobilefront.service.DaoConfig;
import com.tiancheng.mobilefront.utils.DateUtil;

public class JobTrigger implements Job {
	private static final Logger logger = Logger.getLogger(JobTrigger.class);
	private DaoManager daoManager;
	private ApkInfoDAO apkInfoDAO;

	public JobTrigger() {
		daoManager = DaoConfig.getDaoManager();
		apkInfoDAO = (ApkInfoDAO) daoManager.getDao(ApkInfoDAO.class);
	}

	public static void main(String[] args) throws JobExecutionException {
		new JobTrigger().execute(null);
	}

	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// String sql = "select id_,taskName,historyStr from task ";
		try {
			List<TaskInfo> obj = apkInfoDAO.getTaskList();
			for (TaskInfo t : obj) {

				int day = DateUtil.Days();
				// String sql2 =
				// "select count(1) from keep where taskId=? and Days=?";
				BaseBean bb = new BaseBean();
				bb.setDay(day - 1);
				int id = t.getId_();
				bb.setTaskId(id);

				String obj2Str = apkInfoDAO.getKeep(bb);

				String historyStr = t.getHistoryStr();
				JSONObject member1 = new JSONObject();
				if (!StringUtils.isEmpty(historyStr)) {
					member1 = JSONObject.fromObject(historyStr);
				}
				member1.put(day - 1, obj2Str);

				// String sql3 = "update task set historyStr =? where id_ =? ";
				TaskInfo t_tmp = new TaskInfo();
				t_tmp.setHistoryStr(member1.toString());
				t_tmp.setId_(id);

				apkInfoDAO.updateHistoryStr(t_tmp);

				System.out.println("job success = " + member1.toString());

			}

			updateTaskOffSetAll();

			logger.info("job done..........");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新偏移量
	 */
	private void updateTaskOffSetAll() {
		apkInfoDAO.updateTaskOffSetAll();
		logger.info("job done..........");
	}
}
