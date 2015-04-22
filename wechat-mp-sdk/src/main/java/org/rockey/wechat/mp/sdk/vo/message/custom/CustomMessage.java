package org.rockey.wechat.mp.sdk.vo.message.custom;

import org.rockey.wechat.mp.sdk.vo.AbstractToStringBuilder;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author RockeyHoo
 */
public abstract class CustomMessage extends AbstractToStringBuilder
{
    @JSONField(name = "touser")
    private String toUser;

    @JSONField(name = "msgtype")
    private String msgType;

    public CustomMessage() {
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

}
