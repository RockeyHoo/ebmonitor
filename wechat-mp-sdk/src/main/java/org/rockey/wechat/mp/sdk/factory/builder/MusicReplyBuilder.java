package org.rockey.wechat.mp.sdk.factory.builder;

import java.util.List;

import org.rockey.wechat.mp.sdk.vo.ReplyDetail;
import org.rockey.wechat.mp.sdk.vo.message.reply.MusicReply;
import org.rockey.wechat.mp.sdk.vo.message.reply.detail.MusicDetail;
import org.rockey.wechat.mp.sdk.vo.message.reply.Reply;

/**
 *
 * @author RockeyHoo
 */
public class MusicReplyBuilder implements ReplyBuilder {
    @Override
    public Reply buildReply(List<ReplyDetail> replyDetails) {
        ReplyDetail detail = replyDetails.get(0);

        MusicDetail musicDetail = new MusicDetail(
                detail.getTitle(),
                detail.getDescription(),
                detail.getMediaUrl(),
                detail.getUrl(),
                detail.getThumbMediaId());

        return new MusicReply(musicDetail);
    }
}
