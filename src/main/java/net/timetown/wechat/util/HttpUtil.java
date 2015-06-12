package net.timetown.wechat.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtil {
	
	public static String get(String url) {
		try {
			// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			
			// 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到
			connection.connect();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));// 设置编码,否则中文乱码
			String content = "";
			String lines;
			while ((lines = reader.readLine()) != null) {
				content += lines + "\r\n";
			}
			// 断开连接
			reader.close();
			connection.disconnect();
			// 去掉多余的\r\n
			content = "".equals(content) ? "" : content.substring(0, content.length()-2);
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String post(String url, Map<String, String> params) {
		String param = new String();
		for (String key : params.keySet()) {
			try {
				param += (key +"="+URLEncoder.encode(params.get(key), "utf-8") + "&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return post(url, param, "application/x-www-form-urlencoded");
	}
	
	public static String post(String url, String param, String contentType) {
		
		
		try {
			// 打开连接
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

			// 设置是否向connection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true
			connection.setDoOutput(true);
			// 设置请求方式，默认是GET
			connection.setRequestMethod("POST");
			// POST 请求不能使用缓存
			connection.setUseCaches(false);

			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", contentType);
			// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
			// 要注意的是connection.getOutputStream会隐含的进行connect。
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.write(param.getBytes("utf-8"));
			out.flush();
			out.close();// flush and close
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));// 设置编码,否则中文乱码
			String line = new String();
			String content = "";
			while ((line = reader.readLine()) != null) {
				content += line + "\r\n";
			}
			reader.close();
			connection.disconnect();
			// 去掉多余的\r\n
			content = "".equals(content) ? "" : content.substring(0, content.length()-2);
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String encode(String s) {
		try {
			return URLEncoder.encode(s, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return s;
		}
	}
}
