package org.rockey.wechat.mp.sdk.vo.message.custom.detail;

import org.rockey.wechat.mp.sdk.vo.AbstractToStringBuilder;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author RockeyHoo
 */
public class MediaCustomMessageDetail extends AbstractToStringBuilder
{
    @JSONField(name = "media_id")
    private String mediaId;

    protected MediaCustomMessageDetail() {
    }

    public MediaCustomMessageDetail(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
