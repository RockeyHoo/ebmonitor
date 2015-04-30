package org.rockey.wechat.mp.sdk.factory.parser;

import org.rockey.wechat.mp.sdk.util.ReplyUtil;
import org.rockey.wechat.mp.sdk.vo.message.reply.Reply;
import org.rockey.wechat.mp.sdk.vo.push.Push;
import org.rockey.wechat.mp.sdk.vo.push.TextPush;
import org.rockey.wechat.mp.sdk.vo.token.License;

/**
 *
 * @author RockeyHoo
 */
public class TextPushParser implements PushParser {
	private static final License license = new License("wechat", "wx25c3d588ab1f527d", "73a322011c24e5fb51e2d5186b82c3d6");
	
    @Override
    public Reply parse(Push push) {
        if (!(push instanceof TextPush)) {
            return null;
        }

        TextPush textPush = (TextPush) push;

        // TODO please custom it.
        //Reply reply = ReplyUtil.parseReplyDetailWarpper(ReplyUtil.getDummyNewsReplyDetailWarpper());回复news消息
        Reply reply = ReplyUtil.parseReplyDetailWarpper(ReplyUtil.getDummyTextReplyDetailWarpper());
        //Reply reply = ReplyUtil.parseReplyDetailWarpper(ReplyUtil.getDummyTextReplyDetailWarpper(license, textPush.getToUserName(), textPush.getContent()));
        return ReplyUtil.buildReply(reply, textPush);
    }

}
