package com.jala.common.msg;

public enum ResponseMessage {
	OK(0, "正常返回"),
	PARAMETER_NOT_FOUND(-10001, "不可识别的method参数"),
	PARAMETER_NOT_GOOD(-10002, "参数错误"),
	PARSE_XML_ERROR(-10003, "解释xml失败"),		
	DB_OPT_UPDATE_ERROR(-10004, "数据库更新失败"),		
	UNKONWN_ERROR(-99999, "未知错误");


	private static final int BASE_CODE = 0;
	private int code;
	private String message;

	private ResponseMessage(int code, String message) {
		this.code = BASE_CODE + code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
