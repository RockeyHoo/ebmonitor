package org.rockey.wechat.mp.sdk.factory.parser;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.rockey.wechat.mp.sdk.util.ReplyUtil;
import org.rockey.wechat.mp.sdk.vo.push.Push;
import org.rockey.wechat.mp.sdk.vo.EventPushType;
import org.rockey.wechat.mp.sdk.vo.message.reply.Reply;
import org.rockey.wechat.mp.sdk.vo.push.event.EventPush;

/**
 *
 * @author RockeyHoo
 */
public class EventPushParser implements PushParser {
    @Override
    public Reply parse(Push push) {
        if (!(push instanceof EventPush)) {
            return null;
        }

        EventPush eventPush = (EventPush) push;
        String event = eventPush.getEvent();

        EventPushType eventPushType = EnumUtils.getEnum(EventPushType.class, StringUtils.upperCase(event));
        Validate.notNull(eventPushType, "don't-support-%s-event-push", event);

        // TODO please custom it.
        if (eventPushType == EventPushType.SUBSCRIBE) {
            Reply reply = ReplyUtil.parseReplyDetailWarpper(ReplyUtil.getDummyTextReplyDetailWarpper());
            return ReplyUtil.buildReply(reply, eventPush);
        }

        return null;
    }
}
