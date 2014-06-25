package com.jala.agency.mock;

/**
 * 模拟[smsAdd]，短信入库
 * @author heshuang
 *
 */
public class MockSmsAdd {
	
	//public static String url = "http://42.121.16.232:9002/agencyapi/smsData/smsAdd.service";
	//public static String url = "http://42.121.16.222:8002/agencyapi/smsData/smsAdd.service";
	public static String url = "http://127.0.0.1:8089/agencyapi/smsData/smsAdd.service";
	public static String method = "smsAdd";
	public static String sign = "Qwer_!234";
	
	public static void main(String[] args) {
		// 1. 拼装发送消息
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?> \n");
		sb.append("	<MSG>	\n");
		sb.append("	    <DATA>	\n");
		sb.append("	            <REQUESTDATE>20140514</REQUESTDATE>	\n");
		sb.append("	            <REQUESTTYPE>001001</REQUESTTYPE>	\n");
		sb.append("	            <AMOUNT>53.21</AMOUNT>	\n");
		sb.append("	            <TYPE>1001</TYPE>	\n");
		sb.append("	    </DATA>");
		sb.append("	    <DATA>	\n");
		sb.append("	            <REQUESTDATE>20140514</REQUESTDATE>	\n");
		sb.append("	            <REQUESTTYPE>001002</REQUESTTYPE>	\n");
		sb.append("	            <AMOUNT>53.21</AMOUNT>	\n");
		sb.append("	            <TYPE>1001</TYPE>	\n");
		sb.append("	    </DATA>");
		sb.append("	    <DATA>	\n");
		sb.append("	            <REQUESTDATE>20140514</REQUESTDATE>	\n");
		sb.append("	            <REQUESTTYPE>001004</REQUESTTYPE>	\n");
		sb.append("	            <AMOUNT>53.21</AMOUNT>	\n");
		sb.append("	            <TYPE>1002</TYPE>	\n");
		sb.append("	    </DATA>");
		sb.append("</MSG>");
		
		String xmlDoc = PostUtils.postData(sb.toString(),url,method,sign);
		
		System.out.println("返回参数： \n" + xmlDoc);
	}
}
