package org.rockey.wechat.mp.sdk.factory.builder;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.rockey.wechat.mp.sdk.vo.ReplyDetail;
import org.rockey.wechat.mp.sdk.vo.message.reply.TextReply;
import org.rockey.wechat.mp.sdk.vo.message.reply.Reply;

/**
 *
 * @author RockeyHoo
 */
public class TextReplyBuilder implements ReplyBuilder {
    @Override
    public Reply buildReply(List<ReplyDetail> replyDetails) {
        String content = replyDetails.get(0).getDescription();
        if (StringUtils.isEmpty(content)) {
            return null;
        }

        return new TextReply(content);

    }
}
