package org.rockey.wechat.mp.sdk.vo.message.custom;

import org.rockey.wechat.mp.sdk.vo.message.custom.detail.VideoCustomMessageDetail;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *
 * @author RockeyHoo
 */
public class VideoCustomMessage extends CustomMessage {
    @JSONField(name = "video")
    private VideoCustomMessageDetail videoDetail;

    {
        super.setMsgType(CustomMessageType.VIDEO.getMsgType());
    }

    public VideoCustomMessage(VideoCustomMessageDetail videoDetail) {
        this.videoDetail = videoDetail;
    }

    public VideoCustomMessageDetail getVideoDetail() {
        return videoDetail;
    }

    public void setVideoDetail(VideoCustomMessageDetail videoDetail) {
        this.videoDetail = videoDetail;
    }

}
