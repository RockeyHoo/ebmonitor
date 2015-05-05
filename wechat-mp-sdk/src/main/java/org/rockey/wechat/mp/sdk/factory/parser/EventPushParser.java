package org.rockey.wechat.mp.sdk.factory.parser;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.rockey.wechat.mp.sdk.util.ReplyUtil;
import org.rockey.wechat.mp.sdk.vo.EventPushType;
import org.rockey.wechat.mp.sdk.vo.message.reply.Reply;
import org.rockey.wechat.mp.sdk.vo.push.Push;
import org.rockey.wechat.mp.sdk.vo.push.event.EventPush;
import org.rockey.wechat.mp.sdk.vo.push.event.NormalEventPush;

/**
 * @author RockeyHoo
 */
public class EventPushParser implements PushParser
{
    @Override
    public Reply parse(Push push)
    {
        if (!(push instanceof EventPush))
        {
            return null;
        }
        NormalEventPush eventPush = (NormalEventPush) push;
        String event = eventPush.getEvent();
        String eventKey = eventPush.getEventKey();
        EventPushType eventPushType = EnumUtils.getEnum(EventPushType.class, StringUtils.upperCase(event));
        Validate.notNull(eventPushType, "don't-support-%s-event-push", event);

        if (eventPushType == EventPushType.CLICK)
        {
            if (eventKey.equals("fund_click"))
            {
                Reply reply = ReplyUtil.parseReplyDetailWarpper(ReplyUtil.getClickTextReplyDetailWarpper());
                return ReplyUtil.buildReply(reply, eventPush);
            }
        }
        return null;
    }
}
