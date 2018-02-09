package com.tiancheng.mobilefront.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Model_meizu {

	public String id;

	// 手机型号相关
	public String BOARD; // 机型相关
	public String BOOTLOADER;
	public String BRAND;
	public String CPU_ABI;
	public String CPU_ABI2;
	public String DEVICE;
	public String DISPLAY;
	public String FINGERPRINT; // 机型下ROM相关
	public String HARDWARE;
	public String HOST; // 机型下ROM相关
	public String ID; // 机型下ROM相关
	public String MANUFACTURER;
	public String MODEL;
	public String PRODUCT;
	public String RADIO; // 机型ROM相关
	// public String SERIAL; //格式为：8位16进制随机代码
	public String TAGS; // 随机生成：unsigned,debug，user,userdebug
	public String TYPE; // user,eng,userdebug,end
	public String USER;
	public String CODENAME;// 一般为REL
	public String INCREMENTAL;
	public String RELEASE;// 机型ROM相关
	public int SDK_INT;// 机型ROM相关
	public String SDK;// 根据SDK_INT 生成
	// 屏幕相关
	public int Height; // 机型相关
	public int Width; // 机型先关
	public float Density;// 机型相关
	public float ScaleDensity;// 机型相关
	public float DensityDPI;// 机型相关
	public float Xdpi; // 机型相关
	public float Ydpi; // 机型相关
	// 机型数据配置有关,暂时不适用
	public String UA; // 三种修改方法
	public String BaseBand;// 两种修改方法
	public String OSVersion;// 两种修改方法
	public String PROCVersion;// 两种修改方法
	public String CPUINFO;// 两种修改方法
	public String TIME; // 尚不清楚生成原理。

	public static String getTAGS() {
		String[] Strs = new String[] { "unsigned", "debug", "user", "userdebug", "" };
		Random random = new Random();
		int index = random.nextInt(4);
		return Strs[index];
	}

	public static String getTYPE() {
		String[] Strs = new String[] { "user", "eng", "userdebug", "end", "" };
		Random random = new Random();
		int index = random.nextInt(4);
		return Strs[index];
	}

	public static String getCODENAME() {
		String[] Strs = new String[] { "REL", "" };
		Random random = new Random();
		int index = random.nextInt(1);
		return Strs[index];
	}

	public static Map<String, Model_meizu> GetLib() {
		Map<String, Model_meizu> maps = new HashMap<String, Model_meizu>();

		String[] models = new String[] { "M3", "MX4", "MX5", "MX6", "m1 note", "M463C", "M465M", "M465A", "M460A", "M460", "PRO 5", "PRO 6" };
		String[] flymes = new String[] { "Flyme OS 3.1.4 (U16213)", "Flyme OS 4.2.0.3C", "Flyme OS 5.1.8.0A" };

		Random random = new Random();
		int rand = random.nextInt(models.length);
		String model = models[rand];

		Random random2 = new Random();
		int rand2 = random2.nextInt(flymes.length);
		String flyme = flymes[rand2];
		for (int i = 0; i < 1000; i++) {
			insertLib(maps, new String[] {
					model,
					"Meizu",
					model,
					flyme,
					"Meizu",
					model,
					"meizu_" + model,
					"",
					"JOP40D",
					"user",
					"release-keys",
					"flyme",
					"Flyme-Mz",
					model,
					"armeabi-v7a",
					"armeabi",
					"Meizu/meizu_" + model + "/" + model + ":4.2.1/JOP40D/M35X." + flyme + ".1387797517:user/release-key" + "s",
					"REL",
					"M35X.Flyme_OS_3.1.4.1387797517",
					"4.2.1",
					"17",
					"17",
					"86284502",
					"1800",
					"1080",
					"2.625",
					"2.5",
					"400",
					"415.636",
					"415.636",
					"02.1248.00_R09-425",
					"3.4.43-unicom-svn7549",
					"Linux version 3.4.43-unicom-svn7549 (mobileapp@mobileapp) (gcc version 4.6.x-goo" + "gle 20120106 (prerelease) (GCC) ) #3 SMP PREEMPT Mon Dec 23 16:54:53 CST 2013\n",
					"Processor\t: ARMv7 Processor rev 2 srev 0x23 (v7l)\nprocessor\t: 0\nCluster\t\t:" + " CA7\nBogoMIPS\t: 1590.88\n\nprocessor\t: 1\nCluster\t\t: CA7\nBogoMIPS\t: 1590."
							+ "88\n\nprocessor\t: 2\nCluster\t\t: CA7\nBogoMIPS\t: 1590.88\n\nprocessor\t: 3\nC" + "luster\t\t: CA7\nBogoMIPS\t: 1590.88\n\nFeatures\t: swp half thumb fastmult vfp "
							+ "edsp neon vfpv3 tls vfpv4 idiva idivt \nCPU implementer\t: 0x41\nCPU architectur" + "e: 7\nCPU variant\t: 0x0\nCPU part\t: 0xc07\nCPU revision\t: 2\nCPU asv group\t:"
							+ " 4\nCPU asv version\t: 1\nCPU boot cluster: CA15\n\nHardware\t: MX3\nRevision\t:" + " 5410\nDMEM\t\t: D25\nSerial\t\t: 0000000000000000\n", "yidong" });
		}
		return maps;
	}

	public static Model_meizu GetRondom() {
		Map<String, Model_meizu> models = Model_meizu.GetLib();
		Random random = new Random();
		int i = random.nextInt(models.size());
		Model_meizu ModelInfo = models.get(i + "");
		return ModelInfo;
	}

	public static void main(String[] args) {
		GetRondom();
	}

	// 将数组改为机型
	public static void insertLib(Map<String, Model_meizu> maps, String mod[]) {
		System.out.println(mod);
		Model_meizu pm = new Model_meizu();
		pm.BOARD = mod[0];
		pm.BOOTLOADER = mod[7];
		pm.BRAND = mod[1];
		pm.CPU_ABI = mod[14];
		pm.CPU_ABI2 = mod[15];
		pm.DEVICE = mod[2];
		pm.DISPLAY = mod[3];
		pm.FINGERPRINT = mod[16];
		pm.HARDWARE = mod[13];
		pm.HOST = mod[12];
		pm.ID = mod[8];
		pm.MANUFACTURER = mod[4];
		pm.MODEL = mod[5];
		pm.PRODUCT = mod[6];
		pm.RADIO = "";
		pm.TAGS = mod[10];
		pm.TYPE = mod[9];
		pm.USER = mod[11];
		pm.CODENAME = mod[17];
		pm.INCREMENTAL = mod[18];
		pm.RELEASE = mod[19];
		pm.SDK_INT = Integer.parseInt(mod[21].trim());
		pm.SDK = mod[20];
		pm.Height = Integer.parseInt(mod[23].trim());
		pm.Width = Integer.parseInt(mod[24].trim());
		pm.Density = Float.parseFloat(mod[26].trim());
		pm.ScaleDensity = Float.parseFloat(mod[25].trim());
		pm.DensityDPI = Float.parseFloat(mod[27].trim());
		pm.Xdpi = Float.parseFloat(mod[28].trim());
		pm.Ydpi = Float.parseFloat(mod[28].trim());
		pm.UA = "";// UA数据暂时没有
		pm.BaseBand = mod[30];
		pm.OSVersion = mod[31];
		pm.PROCVersion = mod[32];
		pm.CPUINFO = mod[33];
		// pm.TIME = System.currentTimeMillis() -
		// Long.parseLong(RanUtil.getRanNub(5)) + "";
		String DalvikInt = "14.0.0";
		if (pm.SDK_INT > 15)
			DalvikInt = "16.0.0";
		pm.UA = "Dalvik/" + DalvikInt + "(Linux; U; Android " + pm.RELEASE + "; " + pm.MODEL + " Build/" + pm.ID + ")";
		maps.put(maps.size() + "", pm);
	}
}