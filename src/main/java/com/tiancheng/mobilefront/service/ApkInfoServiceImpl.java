package com.tiancheng.mobilefront.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ibatis.dao.client.DaoManager;
import com.tiancheng.mobilefront.bean.BaseBean;
import com.tiancheng.mobilefront.bean.InfoS;
import com.tiancheng.mobilefront.bean.Order;
import com.tiancheng.mobilefront.bean.Pages;
import com.tiancheng.mobilefront.bean.PhoneInfo;
import com.tiancheng.mobilefront.bean.TaskInfo;
import com.tiancheng.mobilefront.dao.ApkInfoDAO;
import com.tiancheng.mobilefront.utils.DXNumCode;
import com.tiancheng.mobilefront.utils.DateUtil;
import com.tiancheng.mobilefront.utils.DingdkjNumCode;
import com.tiancheng.mobilefront.utils.FeiMaNumCode;

public class ApkInfoServiceImpl implements IApkInfoService {
	private DaoManager daoManager;
	private ApkInfoDAO apkInfoDAO;
	private static final Logger logger = Logger.getLogger(ApkInfoServiceImpl.class);

	public ApkInfoServiceImpl() {
		daoManager = DaoConfig.getDaoManager();
		apkInfoDAO = (ApkInfoDAO) daoManager.getDao(ApkInfoDAO.class);
	}

	/**
	 * 获取留存的数据，并更新状态
	 */
	@Override
	public JSONObject keepByServer(String id, int d) {
		int days = DateUtil.Days();

		int keep2 = days - d + 1;

		String keepD = "keep" + d;

		// String sql = "select ROUND(count(*) * t." + keepD +
		// "/100) from task t,keep k where t.id_=k.taskId and k.Days=? and t.id_=?";
		// String sql2 =
		// "select count(*) from task t,keep k where t.id_=k.taskId and k.Days=? and t.id_=? and k.KeepDays=?";

		try {
			Object unExecute = apkInfoDAO.keepByServerUnExecute(keepD, keep2, id);
			Object execute = apkInfoDAO.keepByServerExecute(keep2, id, d + "");
			Double count = Double.valueOf(unExecute == null ? "0" : unExecute.toString()) - Double.valueOf(execute == null ? "0" : execute.toString());
			if (count <= 0) {// 需要做的留存减去做过的留存， 小于等于o代表，这天不需要再做留存了，就不需要再这些下面的代码了
				return null;
			}

			// String sql3 =
			// "select k.* from keep k,task t where t.id_=k.taskId and k.Days=? and t.id_=? and k.KeepDays !=? LIMIT 0,1";
			// Object[][] phones = (Object[][]) null;
			// phones = this.apkInfoDao.executeSqlFind(sql3, new Object[] {
			// keep2, id, d });

			PhoneInfo pi = apkInfoDAO.keepByServer(keep2, id, d + "");
			updateKeep(id, Long.parseLong(pi.getID_()), d);

			JSONObject json = JSONObject.fromObject(pi);

			// 刷留存0， 刷注册留存 4
			if (StringUtils.isEmpty(pi.getRegisterPhone())) {
				json.put("flagShua", "0");
			} else {
				json.put("flagShua", "4");
			}

			// Object[] phone = phones[0];
			// String jsonPhone = toJsonObject(phone);

			return json;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * 更新
	 * 
	 * @param id
	 * @param KeepDays
	 */
	public void updateKeep(String taskId, Long id, int KeepDays) {
		// String sql3 = "update keep k set k.KeepDays=" + KeepDays +
		// " where k.id_=" + id;
		apkInfoDAO.updateKeep(id, KeepDays + "");
		logger.info("任务id：" + taskId + "----," + "KeepDays=" + KeepDays);

	}

	private String toJsonObject(Object[] phone) {
		JSONObject member1 = new JSONObject();
		member1.put("taskId", phone[1]);
		member1.put("KeepDays", phone[2]);
		member1.put("Days", phone[3]);
		member1.put("UseDays", phone[4]);
		member1.put("PackageName", phone[5]);
		member1.put("IMEI", phone[6]);
		member1.put("AndroidID", phone[7]);
		member1.put("GsfId", phone[8]);
		member1.put("AdId", phone[9]);
		member1.put("Mac", phone[10]);
		member1.put("SSID", phone[11]);
		member1.put("BSSID", phone[12]);
		member1.put("Ip", phone[13]);
		member1.put("Line1Number", phone[14]);
		member1.put("CountryIso", phone[15]);
		member1.put("Operator", phone[16]);
		member1.put("OperatorName", phone[17]);
		member1.put("SimSerialNumber", phone[18]);
		member1.put("SubscriberId", phone[19]);
		member1.put("NetWorkType", phone[20]);
		member1.put("BOARD", phone[21]);
		member1.put("BOOTLOADER", phone[22]);
		member1.put("BRAND", phone[23]);
		member1.put("CPU_ABI", phone[24]);
		member1.put("CPU_ABI2", phone[25]);
		member1.put("DEVICE", phone[26]);
		member1.put("DISPLAY", phone[27]);
		member1.put("FINGERPRINT", phone[28]);
		member1.put("HARDWARE", phone[29]);
		member1.put("HOST", phone[30]);
		member1.put("ID", phone[31]);
		member1.put("MANUFACTURER", phone[32]);
		member1.put("MODEL", phone[33]);
		member1.put("PRODUCT", phone[34]);
		member1.put("RADIO", phone[35]);
		member1.put("SERIAL", phone[36]);
		member1.put("TAGS", phone[37]);
		member1.put("TIME", phone[38]);
		member1.put("TYPE", phone[39]);
		member1.put("USER", phone[40]);
		member1.put("CODENAME", phone[41]);
		member1.put("INCREMENTAL", phone[42]);
		member1.put("RELEASE", phone[43]);
		member1.put("SDK_INT", phone[44]);
		member1.put("SDK", phone[45]);
		member1.put("Height", phone[46]);
		member1.put("Width", phone[47]);
		member1.put("Density", phone[48]);
		member1.put("ScaleDensity", phone[49]);
		member1.put("DensityDPI", phone[50]);
		member1.put("Xdpi", phone[51]);
		member1.put("Ydpi", phone[52]);
		member1.put("UA", phone[53]);
		member1.put("BaseBand", phone[54]);
		member1.put("OSVersion", phone[55]);
		member1.put("PROCVersion", phone[56]);
		member1.put("CPUINFO", phone[57]);
		member1.put("Lat", phone[58]);
		member1.put("Lon", phone[59]);
		member1.put("Alt", phone[60]);
		member1.put("Cid", phone[61]);
		member1.put("Lac", phone[62]);
		return member1.toString();
	}

	/**
	 * 判断是否达到了当天设置的最大值
	 */
	@Override
	public String isReachTopOfEveryDay(String taskId) {

		int d = DateUtil.Days();

		try {
			String obj = apkInfoDAO.isReachTopOfEveryDay(d, Integer.valueOf(taskId));
			String objRegister = apkInfoDAO.isReachTopOfEveryDayOfRegist(d, Integer.valueOf(taskId));

			if (StringUtils.isEmpty(objRegister) || "null".equalsIgnoreCase(objRegister)) {
				objRegister = "0";
			}

			if (StringUtils.isEmpty(obj) || "null".equalsIgnoreCase(obj)) {
				return "false";
			} else {
				int num = Integer.valueOf(obj.toString());
				int objRegisterInt = Integer.valueOf(objRegister.toString());
				if (num <= 0 && objRegisterInt <= 0) {
					return "true";
				} else {
					return "false";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	/**
	 * 保存刷量信息
	 */
	@Override
	public String SaveToServer(String phoneInfo) {

		String success = "success";

		PhoneInfo pi = new PhoneInfo();

		int Days = Integer.valueOf(DateUtil.Days());
		int UseDays = Integer.valueOf(DateUtil.Days());
		pi.setDays(Days + "");
		pi.setUseDays(UseDays + "");

		JSONObject json = JSONObject.fromObject(phoneInfo);
		String taskId = json.get("taskId") + "";
		String flagShua = json.get("flagShua") + "";

		if ("0".equals(flagShua) || "3".equals(flagShua) || "4".equals(flagShua)) {
			System.out.println("taskId=" + taskId + "不需要保存，直接返回");
			return "";
		}

		pi.setTaskId(taskId);

		String isTF = isReachTopOfEveryDay(taskId);
		if ("true".equalsIgnoreCase(isTF)) {
			return "";
		}

		// String valdatesql = "select count(*) from task where id_=" +
		// taskId.toString();
		try {
			String countObj = apkInfoDAO.saveToServerValidate(String.valueOf(taskId));
			if (!"1".equals(countObj)) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		String registerPhone = json.getString("registerPhone") + "";
		pi.setRegisterPhone(registerPhone);

		String KeepDays = json.get("KeepDays") + "";
		pi.setKeepDays(KeepDays);
		// Object Days = json.get("Days");
		// Object UseDays = json.get("UseDays");
		String PackageName = json.get("PackageName") + "";
		pi.setPackageName(PackageName);
		String IMEI = json.get("IMEI") + "";
		pi.setIMEI(IMEI);
		String AndroidID = json.get("AndroidID") + "";
		pi.setAndroidID(AndroidID);
		String GsfId = json.get("GsfId") + "";
		pi.setGsfId(GsfId);
		String AdId = json.get("AdId") + "";
		pi.setAdId(AdId);
		String Mac = json.get("Mac") + "";
		pi.setMac(Mac);
		String SSID = json.get("SSID") + "";
		pi.setSSID(SSID);
		String BSSID = json.get("BSSID") + "";
		pi.setBSSID(BSSID);
		String Ip = json.get("Ip") + "";
		pi.setIp(Ip);
		String Line1Number = json.get("Line1Number") + "";
		pi.setLine1Number(Line1Number);
		String CountryIso = json.get("CountryIso") + "";
		pi.setCountryIso(CountryIso);
		String Operator = json.get("Operator") + "";
		pi.setOperator(Operator);
		String OperatorName = json.get("OperatorName") + "";
		pi.setOperatorName(OperatorName);
		String SimSerialNumber = json.get("SimSerialNumber") + "";
		pi.setSimSerialNumber(SimSerialNumber);
		String SubscriberId = json.get("SubscriberId") + "";
		pi.setSubscriberId(SubscriberId);
		String NetWorkType = json.get("NetWorkType") + "";
		pi.setNetWorkType(NetWorkType);
		String BOARD = json.get("BOARD") + "";
		pi.setBOARD(BOARD);
		String BOOTLOADER = json.get("BOOTLOADER") + "";
		pi.setBOOTLOADER(BOOTLOADER);
		String BRAND = json.get("BRAND") + "";
		pi.setBRAND(BRAND);
		String CPU_ABI = json.get("CPU_ABI") + "";
		pi.setCPU_ABI(CPU_ABI);
		String CPU_ABI2 = json.get("CPU_ABI2") + "";
		pi.setCPU_ABI2(CPU_ABI2);
		String DEVICE = json.get("DEVICE") + "";
		pi.setDEVICE(DEVICE);
		String DISPLAY = json.get("DISPLAY") + "";
		pi.setDISPLAY(DISPLAY);
		String FINGERPRINT = json.get("FINGERPRINT") + "";
		pi.setFINGERPRINT(FINGERPRINT);
		String HARDWARE = json.get("HARDWARE") + "";
		pi.setHARDWARE(HARDWARE);
		String HOST = json.get("HOST") + "";
		pi.setHOST(HOST);
		String ID = json.get("ID") + "";
		pi.setID(ID);
		String MANUFACTURER = json.get("MANUFACTURER") + "";
		pi.setMANUFACTURER(MANUFACTURER);
		String MODEL = json.get("MODEL") + "";
		pi.setMODEL(MODEL);
		String PRODUCT = json.get("PRODUCT") + "";
		pi.setPRODUCT(PRODUCT);
		String RADIO = json.get("RADIO") + "";
		pi.setRADIO(RADIO);
		String SERIAL = json.get("SERIAL") + "";
		pi.setSERIAL(SERIAL);
		String TAGS = json.get("TAGS") + "";
		pi.setTAGS(TAGS);
		String TIME = json.get("TIME") + "";
		pi.setTIME(TIME);
		String TYPE = json.get("TYPE") + "";
		pi.setTYPE(TYPE);
		String USER = json.get("USER") + "";
		pi.setUSER(USER);
		String CODENAME = json.get("CODENAME") + "";
		pi.setCODENAME(CODENAME);
		String INCREMENTAL = json.get("INCREMENTAL") + "";
		pi.setINCREMENTAL(INCREMENTAL);
		String RELEASE = json.get("RELEASE") + "";
		pi.setRELEASE(RELEASE);
		String SDK_INT = json.get("SDK_INT") + "";
		pi.setSDK_INT(SDK_INT);
		String SDK = json.get("SDK") + "";
		pi.setSDK(SDK);
		String Height = json.get("Height") + "";
		pi.setHeight(Height);
		String Width = json.get("Width") + "";
		pi.setWidth(Width);
		String Density = json.get("Density") + "";
		pi.setDensity(Density);
		String ScaleDensity = json.get("ScaleDensity") + "";
		pi.setScaleDensity(ScaleDensity);
		String DensityDPI = json.get("DensityDPI") + "";
		pi.setDensityDPI(DensityDPI);
		String Xdpi = json.get("Xdpi") + "";
		pi.setXdpi(Xdpi);
		String Ydpi = json.get("Ydpi") + "";
		pi.setYdpi(Ydpi);
		String UA = json.get("UA") + "";
		pi.setUA(UA);
		String BaseBand = json.get("BaseBand") + "";
		pi.setBaseBand(BaseBand);
		String OSVersion = json.get("OSVersion") + "";
		pi.setOSVersion(OSVersion);
		String PROCVersion = json.get("PROCVersion") + "";
		pi.setPROCVersion(PROCVersion);
		String CPUINFO = json.get("CPUINFO") + "";
		pi.setCPUINFO(CPUINFO);
		String Lat = json.get("Lat") + "";
		pi.setLat(Lat);
		String Lon = json.get("Lon") + "";
		pi.setLon(Lon);
		String Alt = json.get("Alt") + "";
		pi.setAlt(Alt);
		String Cid = json.get("Cid") + "";
		pi.setCid(Cid);
		String Lac = json.get("Lac") + "";
		pi.setLac(Lac);
		// String sql =
		// "insert into keep(taskId,KeepDays,Days,UseDays,PackageName,IMEI,AndroidID,GsfId,AdId,Mac,SSID,BSSID,Ip,Line1Number,CountryIso,Operator,OperatorName,SimSerialNumber,SubscriberId,NetWorkType,BOARD,BOOTLOADER,BRAND,CPU_ABI,CPU_ABI2,DEVICE,DISPLAY,FINGERPRINT,HARDWARE,HOST,ID,MANUFACTURER,MODEL,PRODUCT,RADIO,SERIAL,TAGS,TIME,TYPE,USER,CODENAME,INCREMENTAL,RELEASE_,SDK_INT,SDK,Height,Width,Density,ScaleDensity,DensityDPI,Xdpi,Ydpi,UA,BaseBand,OSVersion,PROCVersion,CPUINFO,Lat,Lon,Alt,Cid,Lac)"
		// +
		// "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// this.phoneInfoDao.executeSql(sql, new Object[] { taskId, KeepDays,
		// Days, UseDays, PackageName, IMEI, AndroidID, GsfId, AdId, Mac, SSID,
		// BSSID, Ip, Line1Number, CountryIso, Operator,
		// OperatorName, SimSerialNumber, SubscriberId, NetWorkType, BOARD,
		// BOOTLOADER, BRAND, CPU_ABI, CPU_ABI2, DEVICE, DISPLAY, FINGERPRINT,
		// HARDWARE, HOST, ID, MANUFACTURER, MODEL, PRODUCT,
		// RADIO, SERIAL, TAGS, TIME, TYPE, USER, CODENAME, INCREMENTAL,
		// RELEASE, SDK_INT, SDK, Height, Width, Density, ScaleDensity,
		// DensityDPI, Xdpi, Ydpi, UA, BaseBand, OSVersion,
		// PROCVersion, CPUINFO, Lat, Lon, Alt, Cid, Lac });

		// PhoneInfo pi = (PhoneInfo) JSONObject.toBean(json, PhoneInfo.class);
		apkInfoDAO.SaveToServer(pi);
		return success;

	}

	public List<TaskInfo> getTaskList() {
		// List list = new ArrayList();
		// String sql = "select * from task";
		// try {
		List<TaskInfo> obj = apkInfoDAO.getTaskList();

		// for (Object[] arr : obj) {
		// TaskInfo t = new TaskInfo();
		// t.setId_((Integer) arr[0]);
		// t.setTaskName((String) arr[1]);
		// if (arr[2] == null) {
		// t.setTopOfEveryDay("0");
		// } else {
		// t.setTopOfEveryDay(String.valueOf(arr[2]));
		// }
		// t.setKeep1((String) arr[3]);
		// t.setKeep2((String) arr[4]);
		// t.setKeep3((String) arr[5]);
		// t.setKeep4((String) arr[6]);
		// t.setKeep5((String) arr[7]);
		// t.setKeep6((String) arr[8]);
		// t.setKeep7((String) arr[9]);
		// t.setKeep8((String) arr[10]);
		// t.setKeep9((String) arr[11]);
		// t.setKeep10((String) arr[12]);
		// t.setKeep11((String) arr[13]);
		// t.setKeep12((String) arr[14]);
		// t.setKeep13((String) arr[15]);
		// t.setKeep14((String) arr[16]);
		// t.setKeep15((String) arr[17]);
		// t.setKeep16((String) arr[18]);
		// t.setKeep17((String) arr[19]);
		// t.setKeep18((String) arr[20]);
		// t.setKeep19((String) arr[21]);
		// t.setKeep20((String) arr[22]);
		// t.setKeep21((String) arr[23]);
		// t.setKeep22((String) arr[24]);
		// t.setKeep23((String) arr[25]);
		// t.setKeep24((String) arr[26]);
		// t.setKeep25((String) arr[27]);
		// t.setKeep26((String) arr[28]);
		// t.setKeep27((String) arr[29]);
		// t.setKeep28((String) arr[30]);
		// t.setKeep29((String) arr[31]);
		// t.setKeep30((String) arr[32]);
		// t.setKeep31((String) arr[33]);
		// list.add(t);
		// }

		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return obj;

	}

	public void updateTask(TaskInfo t) {
		apkInfoDAO.updateTask(t);
	}

	public void addTask(TaskInfo t) {
		apkInfoDAO.addTask(t);
	}

	public void deleteTask(String id) {
		apkInfoDAO.deleteTask(id);

	}

	public List<TaskInfo> getKeepList(int start, int end) {
		// String sql = "select id_,taskName from task LIMIT " + m + "," + n +
		// "";
		List<TaskInfo> list = new ArrayList<TaskInfo>();
		Pages p = new Pages();
		p.setStart(start);
		p.setEnd(end);
		try {
			List<TaskInfo> tList = apkInfoDAO.getTaskByLimit(p);

			for (TaskInfo ti : tList) {

				String info = "";
				int day = DateUtil.Days();
				// String sql2 =
				// "select count(1) from keep where taskId=? and Days=?";
				BaseBean bb = new BaseBean();
				bb.setTaskId(ti.getId_());
				bb.setDay(day);

				String obj2Str = apkInfoDAO.getKeep(bb);

				info += "今日新增：" + obj2Str + ",";

				int countKeep = 0;
				for (int i = 1; i < 32; i++) {
					countKeep += countKeep(ti.getId_() + "", i);
				}

				String str = ti.getTaskName() + "留存剩余：" + countKeep + "---" + info;

				// String sql3 = "select historyStr from task where id_=?";
				String historyStrJson = apkInfoDAO.getHistoryStr(ti.getId_());

				if (!StringUtils.isEmpty(historyStrJson)) {

					JSONObject jo = JSONObject.fromObject(historyStrJson);
					for (int i = 1; i < 32; i++) {
						Object ob = jo.get((day - i) + "");
						if (ob != null) {
							str += i + "天前：" + ob + ",";
						}
					}
				}

				ti.setTaskName(str);
				ti.setId_(Integer.valueOf(ti.getId_()));
				list.add(ti);
				System.out.println(str);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 计算留存剩余
	 * 
	 * @param id
	 * @param d
	 * @return
	 * @throws Exception
	 */
	public Double countKeep(String id, int d) {
		int days = DateUtil.Days();

		int keep2 = days - d + 1;

		String keepD = "keep" + d;

		Double unExecuteD = 0D;

		// d需要跑多少留存
		// String sql = "select ROUND(count(*) * t."
		// + keepD
		// +
		// "/100) from task t,keep k where t.id_=k.taskId and k.Days=? and t.id_=?";

		String unExecute = apkInfoDAO.keepByServerUnExecute(keepD, keep2, id);
		unExecuteD = Double.valueOf(unExecute == null ? "0" : unExecute);

		if (unExecuteD == 0D) {
			return 0D;
		}

		// 已经跑了多少留存
		// String sql2 =
		// "select count(*) from task t,keep k where t.id_=k.taskId and k.Days=? and t.id_=? and k.KeepDays=?";

		BaseBean bb = new BaseBean();
		bb.setDay(keep2);
		bb.setId(Long.parseLong(id));
		bb.setKeepDays(d + "");

		String execute = apkInfoDAO.executeKeep(bb);

		Double count = unExecuteD - Double.valueOf(execute == null ? "0" : execute.toString());
		return count;
	}

	public void deleteKeep(String id_) {
		apkInfoDAO.deleteKeep(id_);
	}

	/**
	 * 新增分2种情况 注册还是非注册
	 * 
	 * @param id
	 * @return
	 */
	public JSONObject newOrRegister(String id) {
		JSONObject j = new JSONObject();
		boolean b = apkInfoDAO.isNeedRegister(id);
		if (!b) {
			j.put("flagShua", "1");
			return j;
		} else {
			j.put("flagShua", "2");
			return j;
		}

	}

	/**
	 * 通过手机号获取验证码
	 * 
	 * @param phoneNum
	 * @return
	 */
	public String get599PhoneCode(String phoneNum) {
		return FeiMaNumCode.get599PhoneCode(phoneNum);
	}

	/**
	 * 获取token
	 * 
	 * @param phoneNum
	 * @return
	 */
	public String get599Token() {
		return DingdkjNumCode.get599Token();
	}

	/**
	 * 路由
	 * 
	 * @param registIdValue
	 * 
	 * @return
	 */
	public String getPhoneNum(String registIdValue) {

		if (!"0".equals(registIdValue)) {
			String phoneNum = FeiMaNumCode.get599PhoneNum(registIdValue);
			if (!phoneNum.startsWith("1")) {
				if ("17693".equalsIgnoreCase(registIdValue)) {
					registIdValue = "3045";
				}
				return DingdkjNumCode.get599PhoneNum(registIdValue);
			}
			return phoneNum;
		}

		String num = DingdkjNumCode.get599PhoneNum(registIdValue);
		if (!num.startsWith("1")) {
			return DXNumCode.get599PhoneNum(registIdValue);
		} else {
			return num;
		}
	}

	public String updateKeepPhone(String imei, String phoneNum) {
		apkInfoDAO.updateKeepPhone(imei, phoneNum);
		return "";
	}

	/**
	 * 生成结算数据
	 * 
	 * @param id_
	 * @param taskName
	 * @param price
	 */
	public void addSellement(String id_, String taskName, String price) {

		Order order = new Order();
		order.setTaskName(taskName);

		TaskInfo ti = apkInfoDAO.getKeepsByTaskId(id_);

		String num = ti.getKeep1();
		String beginDate = ti.getKeep3();
		String endDate = ti.getKeep2();
		order.setBeginDate(beginDate);
		order.setEndDate(endDate);
		int numI = Integer.valueOf(num);
		order.setNum(numI);

		if (StringUtils.isEmpty(price)) {
			price = "0";
		}
		Double priceD = Double.parseDouble(price);
		order.setPrice(priceD);

		order.setMoney(priceD * numI);

		String registers = apkInfoDAO.queryAllRegisters(id_);
		order.setRegisters(registers);

		order.setDays(Integer.valueOf(endDate) - Integer.valueOf(beginDate) + "");

		apkInfoDAO.addSellement(order);

	}

	/**
	 * 从数据库获取手机信息
	 * 
	 * @return
	 */
	public PhoneInfo queryAndroidInfosByTaskId(String taskId) {
		return apkInfoDAO.queryAndroidInfosByTaskId(taskId);
	}

	public InfoS queryInfosById() {

		int r = (int) (1 + Math.random() * (107156 - 1 + 1));
		logger.info("随机数为=" + r);

		return apkInfoDAO.queryInfosById(r + "");
	}

}