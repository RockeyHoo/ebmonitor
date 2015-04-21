package org.usc.wechat.mp.sdk.vo.biz;

import org.usc.wechat.mp.sdk.vo.JsonRtn;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author RockeyHoo
 */
public class BizJsonRtn extends JsonRtn {

    //普通用户的标识，对当前公众号唯一 
    @JSONField(name = "openid")
    private String openId;

    @JSONField(name = "content")
    private String content;

    public BizJsonRtn() {
    }
    
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


}
