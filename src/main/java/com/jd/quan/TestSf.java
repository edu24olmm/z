package com.jd.quan;

import com.google.common.base.Joiner;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestSf {
    private static final Logger logger = Logger.getLogger(TestSf.class);

    public static void main(String[] args) {
        aa();
    }

    private static void aa() {
        // 定义申请获得的appKey和appSecret
        String appkey = "XXXXXXXX";
        String secret = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

// 创建参数表
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("app_key", appkey);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));//使用中国时间，以免时区不同导致认证错误
        paramMap.put("timestamp", format.format(new Date()));

// 对参数名进行排序
        String[] keyArray = paramMap.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);

// 拼接有序的参数名-值串
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(secret);
        for (String key : keyArray) {
            stringBuilder.append(key).append(paramMap.get(key));
        }

        stringBuilder.append(secret);
        String codes = stringBuilder.toString();

// MD5编码并转为大写， 这里使用的是Apache codec
        String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(codes).toUpperCase();

        paramMap.put("sign", sign);

// 拼装请求头Proxy-Authorization的值，这里使用 guava 进行map的拼接
        String authHeader = "MYH-AUTH-MD5 " + Joiner.on('&').withKeyValueSeparator("=").join(paramMap);

        System.out.println(authHeader);

//接下来使用蚂蚁动态代理进行访问

//这里以jsoup为例，将authHeader放入请求头中即可
//原版的jsoup设置代理不方便，这里使用E-lai提供的修改版(https://github.com/E-lai/jsoup-proxy)
//注意authHeader必须在每次请求时都重新计算，要不然会因为时间误差而认证失败
//        Document doc = Jsonp.connect("http://1212.ip138.com/ic.asp").proxy("test.proxy.mayidaili.com", 8123, null).header("Proxy-Authorization", authHeader).get();
    }

}