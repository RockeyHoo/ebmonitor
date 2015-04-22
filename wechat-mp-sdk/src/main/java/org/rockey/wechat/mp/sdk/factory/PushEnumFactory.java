package org.rockey.wechat.mp.sdk.factory;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.rockey.wechat.mp.sdk.vo.push.ImagePush;
import org.rockey.wechat.mp.sdk.vo.push.VoicePush;
import org.rockey.wechat.mp.sdk.vo.push.event.NormalEventPush;
import org.rockey.wechat.mp.sdk.factory.parser.EventPushParser;
import org.rockey.wechat.mp.sdk.factory.parser.ImagePushParser;
import org.rockey.wechat.mp.sdk.factory.parser.LinkPushParser;
import org.rockey.wechat.mp.sdk.factory.parser.LocationPushParser;
import org.rockey.wechat.mp.sdk.factory.parser.PushParser;
import org.rockey.wechat.mp.sdk.factory.parser.TextPushParser;
import org.rockey.wechat.mp.sdk.factory.parser.VideoPushParser;
import org.rockey.wechat.mp.sdk.factory.parser.VoicePushParser;
import org.rockey.wechat.mp.sdk.util.XmlUtil;
import org.rockey.wechat.mp.sdk.vo.EventPushType;
import org.rockey.wechat.mp.sdk.vo.message.reply.Reply;
import org.rockey.wechat.mp.sdk.vo.push.LinkPush;
import org.rockey.wechat.mp.sdk.vo.push.LocationPush;
import org.rockey.wechat.mp.sdk.vo.push.Push;
import org.rockey.wechat.mp.sdk.vo.push.TextPush;
import org.rockey.wechat.mp.sdk.vo.push.VideoPush;

/**
 *
 * @author RockeyHoo
 */
public enum PushEnumFactory {
    TEXT(TextPush.class, new TextPushParser()),
    IMAGE(ImagePush.class, new ImagePushParser()),
    LINK(LinkPush.class, new LinkPushParser()),
    LOCATION(LocationPush.class, new LocationPushParser()),
    VOICE(VoicePush.class, new VoicePushParser()),
    VIDEO(VideoPush.class, new VideoPushParser()),
    EVENT(NormalEventPush.class, new EventPushParser()) {
        @Override
        public Class<? extends Push> getPushClass(String message) {
            EventPushType eventPushType = getEventPushType(message);
            return eventPushType != null ? eventPushType.getEventPushClass() : null;
        }

        private EventPushType getEventPushType(String message) {
            String eventPushTypeStr = StringUtils.substringBetween(message, "<Event><![CDATA[", "]]></Event>");
            return EnumUtils.getEnum(EventPushType.class, StringUtils.upperCase(eventPushTypeStr));
        }
    };

    private Class<? extends Push> pushClass;
    private PushParser pushParser;

    private PushEnumFactory(Class<? extends Push> pushClass, PushParser pushParser) {
        this.pushClass = pushClass;
        this.pushParser = pushParser;
    }

    public Class<? extends Push> getPushClass(String message) {
        return pushClass;
    }

    public Push convert(String message) {
        Object unmarshal = XmlUtil.unmarshal(message, getPushClass(message));
        return unmarshal != null ? (Push) unmarshal : null;
    }

    public Reply parse(Push push) {
        return push != null ? pushParser.parse(push) : null;
    }

}
