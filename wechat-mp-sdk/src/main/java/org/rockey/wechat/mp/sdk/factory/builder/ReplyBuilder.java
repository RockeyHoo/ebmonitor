package org.rockey.wechat.mp.sdk.factory.builder;

import java.util.List;

import org.rockey.wechat.mp.sdk.vo.ReplyDetail;
import org.rockey.wechat.mp.sdk.vo.message.reply.Reply;

/**
 *
 * @author RockeyHoo
 */
public interface ReplyBuilder {
    Reply buildReply(List<ReplyDetail> replyDetails);
}
