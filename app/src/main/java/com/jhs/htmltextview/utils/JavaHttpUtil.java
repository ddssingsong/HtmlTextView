package com.jhs.htmltextview.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 基于Java提供HTTP网络连接工具类,主要提供了两个方法
 *
 * @author yuanc
 * @see JavaHttpUtil get {@link #httpGet(String, HashMap)};
 * @see JavaHttpUtil post {@link #httpPost(String, HashMap)};
 */
public class JavaHttpUtil {
    public static InputStream httpGet(String host, HashMap<String, String> params) {
        String url = generateUrl(host, params);
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setUseCaches(false); // 不使用缓存
            conn.setConnectTimeout(5000);// 此网络连接连接超时时间
            conn.setReadTimeout(3000);// 此网络连接读取超时时间
            conn.setRequestMethod("GET"); // 此网络连接请求方式
            conn.setDoInput(true); // 此网络连接支持获取数据
            conn.setDoOutput(false);// 此网络连接支持上传数据
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) { // 200
                return conn.getInputStream();
            }
        } catch (Exception e) {
            LogUtil.d("JavaHttpUtil", "httpGet exception \n" + url);
        }
        return null;
    }

    /**
     * 拼接链接完整URL
     */
    private static String generateUrl(String url, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder(url);
        // web接口: url?key=val&key=val&key=val;
        if (null != params) {
            urlBuilder.append("?");
            Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> param = iterator.next();
                String key = param.getKey();
                String value = param.getValue();
                urlBuilder.append(key).append('=').append(value);
                if (iterator.hasNext()) {
                    urlBuilder.append('&');
                }
            }
        }
        return urlBuilder.toString();
    }

    public static InputStream httpPost(String host, HashMap<String, String> param) {
        try {
            URL urlObj = new URL(host);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setUseCaches(false); // 不使用缓存
            conn.setConnectTimeout(5000);// 此网络连接连接超时时间
            conn.setReadTimeout(3000);// 此网络连接读取超时时间
            conn.setRequestMethod("POST"); // 此网络连接请求方式
            conn.setDoInput(true); // 此网络连接支持获取数据
            conn.setDoOutput(true);// 此网络连接不支持上传数据
            // 此网络连接将上传表单信息
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStream outStream = conn.getOutputStream();
            if (param != null) {
                outStream.write(mapToString(param).getBytes());
            }
            conn.connect();
            if (conn.getResponseCode() == 200) {
                return conn.getInputStream();
            }
        } catch (Exception e) {
            LogUtil.d("JavaHttpUtil", "httpPost exception \n" + host);
        }
        return null;
    }

    /**
     * 将HasmMap内数据按key:val方式拼接链接出字符串
     */
    private static String mapToString(HashMap<String, String> param) {
        // key=val&key=val&key=val
        StringBuffer strb = new StringBuffer();
        Set<String> keys = param.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String val = param.get(key);
            strb.append(key);
            strb.append("=");
            strb.append(val);
            strb.append("&");
        }
        strb.delete(strb.length() - 1, strb.length());
        return strb.toString();
    }
}
