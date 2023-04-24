package com.summit.constant.common.util.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 调用外部接口类
 * @author wwq
 *
 */
@Component
public class HttpUtil {
	private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);
	/**
	 * 不带请求头信息(token等)
	 * 发送http请求 param urlParam
	 * 
	 * @param requestType
	 * @return
	 */
	public static String sendRequest(String urlParam, String requestType) {
		HttpURLConnection con = null;
		BufferedReader buffer = null;
		StringBuffer resultBuffer = null;
		try {
			URL url = new URL(urlParam);
			// 得到连接对象
			con = (HttpURLConnection) url.openConnection();
			// 设置请求类型
			con.setRequestMethod(requestType);
			// 设置请求需要返回的数据类型和字符集类型
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			// 允许写出
			con.setDoOutput(true);
			// 允许读入
			con.setDoInput(true);
			// 不使用缓存
			con.setUseCaches(false);
			// 得到响应码
			int responseCode = con.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 得到响应流
				InputStream inputStream = con.getInputStream();
				// 将响应流转换成字符串
				resultBuffer = new StringBuffer();
				String line;
				buffer = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				while ((line = buffer.readLine()) != null) {
					resultBuffer.append(line);
				}
				return resultBuffer.toString();
			}

		} catch (Exception e) {
			LOG.error(e.toString());
		}
		return "";
	}
	
	/**
	 * 带 token 等信息
	 * 发送http请求 param urlParam
	 * 
	 * @param requestType
	 * @return
	 */
	public static String sendRequests(String urlParam, String requestType,String token) {
		HttpURLConnection con = null;
		BufferedReader buffer = null;
		StringBuffer resultBuffer = null;
		try {
			
			URL url = new URL(urlParam);
			// 得到连接对象
			con = (HttpURLConnection) url.openConnection();
			// 设置请求类型
			con.setRequestMethod(requestType);
			// 设置请求需要返回的数据类型和字符集类型
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			con.addRequestProperty("Host", url.getHost());//设置头信息
			con.addRequestProperty("Connection", "keep-alive");
			con.addRequestProperty("Accept", "*/*");
			con.addRequestProperty("Cookie", "token="+token);//设置获取的cookie
			con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64)");
			
			// 允许写出
			con.setDoOutput(true);
			// 允许读入
			con.setDoInput(true);
			// 不使用缓存
			con.setUseCaches(false);
			// 得到响应码
			int responseCode = con.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 得到响应流
				InputStream inputStream = con.getInputStream();
				// 将响应流转换成字符串
				resultBuffer = new StringBuffer();
				String line;
				buffer = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				while ((line = buffer.readLine()) != null) {
					resultBuffer.append(line);
				}
				return resultBuffer.toString();
			}

		} catch (Exception e) {
			LOG.error(e.toString());
		}
		return "";
	}


}