package org.rockey.wechat.mp.sdk.factory;

import org.rockey.wechat.mp.sdk.factory.builder.*;
import org.rockey.wechat.mp.sdk.vo.ReplyDetail;
import org.rockey.wechat.mp.sdk.vo.message.reply.Reply;

import java.util.List;

/**
 *
 * @author RockeyHoo
 */
public enum ReplyEnumFactory {
    TEXT("text", new TextReplyBuilder()),
    NEWS("news", new NewsReplyBuilder()),
    MUSIC("music", new MusicReplyBuilder()),
    IMAGE("image", new ImageReplyBuilder()),
    VOICE("voice", new VoiceReplyBuilder()),
    VIDEO("video", new VideoReplyBuilder());

    private String replyType;
    private ReplyBuilder replyBuilder;

    private ReplyEnumFactory(String replyType, ReplyBuilder replyBuilder) {
        this.replyType = replyType;
        this.replyBuilder = replyBuilder;
    }

    public String getReplyType() {
        return replyType;
    }

    public Reply buildReply(List<ReplyDetail> replyDetails) {
        if (replyDetails == null || replyDetails.isEmpty()) {
            return null;
        }

        return replyBuilder.buildReply(replyDetails);
    }

}
