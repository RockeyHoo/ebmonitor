package com.jala.agency.mock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PostUtils {
	
	@SuppressWarnings("rawtypes")
	public static String postData(String meg ,String url,String method,String sign) {
		// 1. 编码
		String encodeContent = meg;
		
		// 2. 添加method
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("METHOD", method);
		parameter.put("CONTENT", encodeContent);
		parameter.put("SIGN", sign);
				
		if (parameter == null || parameter.size() <= 0)
			throw new IllegalArgumentException("Post 参数为空");

		URL u = null;
		HttpURLConnection con = null;

		String data = "";
		StringBuffer sb = new StringBuffer();
		// for结构 自动生成
		for (Iterator iterator = parameter.keySet().iterator(); iterator
				.hasNext();) {
			String key = (String) iterator.next();
			String value = parameter.get(key);
			sb.append("&" + key + "=" + value);
		}
		data = sb.substring(1);
		StringBuffer buffer = new StringBuffer();

		// 尝试发送请求
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			osw.write(data);
			osw.flush();
			osw.close();

			// 读取返回内容
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}

		return buffer.toString();
	}
}
