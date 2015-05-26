package org.rockey.wechat.mp.sdk.util.platform;

import com.google.common.collect.ImmutableList;
import org.rockey.wechat.mp.sdk.vo.media.MediaJsonRtn;
import org.rockey.wechat.mp.sdk.vo.media.NewsMedia;
import org.rockey.wechat.mp.sdk.vo.media.NewsMediaDetail;
import org.rockey.wechat.mp.sdk.vo.message.custom.*;
import org.rockey.wechat.mp.sdk.vo.message.custom.detail.*;
import org.rockey.wechat.mp.sdk.vo.token.License;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RockeyHoo
 */
public class MessageUtilTest
{
    private static final License license = new License("test", "wxafc93a29c1e2a59f", "5613787a72659cf3fae3bf1a5152b17b");

    public static void main(String[] args)
    {
    }

    private static void testCustomMessage()
    {
        TextCustomMessageDetail textDeatil = new TextCustomMessageDetail("t_content");
        CustomMessage textNews = new TextCustomMessage(textDeatil);
        doSendMsg(textNews);
        MediaCustomMessageDetail imageDetail = new MediaCustomMessageDetail("mrT269LYOZbwSp8bMsMmkNL0tcW0IroqMyc2Y95qilM6H3M69V9sSI1F0bV9_MP4");
        ImageCustomMessage imageCustomMessage = new ImageCustomMessage(imageDetail);
        doSendMsg(imageCustomMessage);
        MediaCustomMessageDetail voiceDetail = new MediaCustomMessageDetail("voice_mediaId");
        VoiceCustomMessage voiceCustomMessage = new VoiceCustomMessage(voiceDetail);
        doSendMsg(voiceCustomMessage);
        VideoCustomMessageDetail videoDetail = new VideoCustomMessageDetail("video_mediaId", "video_title", "video_description");
        VideoCustomMessage videoCustomMessage = new VideoCustomMessage(videoDetail);
        doSendMsg(videoCustomMessage);
        MusicCustomMessageDetail musicDetail = new MusicCustomMessageDetail("m_title", "m_description", "m_musicUrl", "m_hQMusicUrl", "m_thumbMediaId");
        MusicCustomMessage musiceCustomMessage = new MusicCustomMessage(musicDetail);
        doSendMsg(musiceCustomMessage);
        List<ArticleCustomMessageDetail> articles = new ArrayList<ArticleCustomMessageDetail>();
        articles.add(new ArticleCustomMessageDetail("a_title1", "a_description1", "a_picUrl1", "a_url1"));
        articles.add(new ArticleCustomMessageDetail("a_title2", "a_description2", "a_picUrl2", "a_url2"));
        articles.add(new ArticleCustomMessageDetail("a_title3", "a_description3", "a_picUrl3", "a_url3"));
        ArticleCustomMessage articleCustomMessage = new ArticleCustomMessage(new ArticlesCustomMessageDetail(articles));
        doSendMsg(articleCustomMessage);
    }

    private static void doSendMsg(CustomMessage msg)
    {
        msg.setToUser("oVDIDt3_SMFnLBBfmQtr67oYT3NI");
        MessageUtil.sendCustomMessage(license, msg);
    }

    private static void testMassMessage()
    {
        String mediaId = "OS3XEqCEjSgIzp_ggt5yLihCThfcMM2NafRgWj44tRSKjjNGvHDxQbTZ7nIpEhlI";
        System.out.println(MessageUtil.sendMassMessageByGroup(license, "1", mediaId));
        List<String> openIds = ImmutableList.of("oVDIDt3_SMFnLBBfmQtr67oYT3NI", "oVDIDt_SyuhqP7WF7zliVZrqY2wY");
        System.out.println(MessageUtil.sendMassMessageByUsers(license, openIds, mediaId));
        System.out.println(MessageUtil.deleteMassMessage(license, 34182));
    }

    private static void testPP()
    {
        License license = new License("test", "wx25c3d588ab1f527d", "de3a3cf8f6f632d8304b924ce2b83c89");
        MessageUtil.sendMassMessageByUsers(license, buildSendList(), "media");
    }

    private static List<String> buildSendList()
    {
        List<String> openids = new ArrayList<String>();
        openids.add("o6q3djvDpI-JfuNuozEFeDwnvFwk");
        return openids;
    }

    public static void upload()
    {
        License license = new License("test", "wx25c3d588ab1f527d", "de3a3cf8f6f632d8304b924ce2b83c89");
        List<NewsMediaDetail> articles = new ArrayList<NewsMediaDetail>();
        NewsMediaDetail detail = new NewsMediaDetail();
        detail.setTitle("泉桥资讯");
        detail.setThumbMediaId("");
        detail.setAuthor("RockeyHoo");
        detail.setContent("全桥资讯最新内容");
        detail.setContentSourceUrl("http://121.42.42.197/wechat/asset/fund.service?fId=1");
        articles.add(detail);
        MediaJsonRtn rtn2 = MediaUtil.uploadNewsMedia(license, new NewsMedia(articles));
        System.out.println(rtn2);
        System.out.println(rtn2.getMediaId());
    }

}
