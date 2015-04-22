package org.rockey.wechat.mp.sdk.vo.message.mass;

import org.rockey.wechat.mp.sdk.vo.JsonRtn;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author RockeyHoo
 */
public class MassMessageJsonRtn extends JsonRtn {
    @JSONField(name = "type")
    private String type;

    @JSONField(name = "msg_id")
    private long msgId;

    public MassMessageJsonRtn() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

}
