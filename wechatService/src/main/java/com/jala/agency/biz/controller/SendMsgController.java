package com.jala.agency.biz.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;


@Controller("sendMsgController")
public class SendMsgController extends AbstractController {
	private static final Log LOG = LogFactory.getLog(SendMsgController.class);
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOG.info("===============接受到获取请求===============");
		request.setCharacterEncoding("UTF-8");
		LOG.info("requestURL =[ " + request.getRequestURI() + "?"
				+ request.getRemoteAddr() + " ] ");
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
        Map<String,String> replyMap = new HashMap<String,String>();
		try {

			Map<String,String> params = new HashMap<String,String>();
			
			// 打印参数列表			
			Enumeration names = request.getParameterNames();
			if(names.hasMoreElements()){
			   while (names.hasMoreElements()) {
			    String paramName = (String) names.nextElement();
			    String paramValue = request.getParameter(paramName);
			    //将所有参数转为大写
			    params.put(paramName.toUpperCase(), paramValue);
			    
			    LOG.info("Request Parameter：" + paramName + "=" + paramValue);
			   }			   
			} else {
				LOG.warn("return error,请求参数为空");
				respContentMSG(replyMap, "FAIL", "参数为空");
				jsonView.setAttributesMap(replyMap);
				return new ModelAndView(jsonView);
			}
			if(!params.containsKey("OPENID") || !params.containsKey("ACCESS_TOKEN") || !params.containsKey("MSG")) {
				LOG.warn("return error,请求参数错误");
				respContentMSG(replyMap, "FAIL", "参数错误");
				jsonView.setAttributesMap(replyMap);
				return new ModelAndView(jsonView);
			}
			replyMap.put("openid", params.get("OPENID"));
			replyMap.put("content","biz接受到消息=" + params.get("MSG")+", 回复消息：你好！");
			LOG.info("biz接受到消息=" + params.get("MSG")+", 回复消息：你好！");
			LOG.info("reply map ===> " + replyMap);
			jsonView.setAttributesMap(replyMap);
			return new ModelAndView(jsonView);
		} catch (Exception e) {
			LOG.error("系统异常",e);
			LOG.error("return error;-99999系统异常");
			respContentMSG(replyMap, "FAIL", "-99999系统异常");
			jsonView.setAttributesMap(replyMap);
			return new ModelAndView(jsonView);
		}
	}

	public void respContentMSG(Map<String,String> replyMap ,String ret,String msg) {
		replyMap.put("errcode", ret);
		replyMap.put("errmsg", msg);
	}
	
	public static boolean isValidTime(String timeLine) throws ParseException{
		boolean isValid = true;
		timeLine = StringUtils.trimToNull(timeLine);
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateN = sf.format(date) + " " + timeLine;
		Date rightNow = sp.parse(dateN);
		isValid = rightNow.getTime() - date.getTime() > 0;
		return isValid;
	}
	
}
