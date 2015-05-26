/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.task;

import org.apache.commons.lang3.StringUtils;
import org.rockey.wechat.mp.sdk.qqasset.QqassetEnumFactory;
import org.rockey.wechat.mp.sdk.util.platform.MediaUtil;
import org.rockey.wechat.mp.sdk.util.platform.MessageUtil;
import org.rockey.wechat.mp.sdk.vo.media.*;
import org.rockey.wechat.mp.web.service.AssetService;
import org.rockey.wechat.mp.web.util.Constants;
import org.rockey.wechat.mp.web.vo.WechatMediasBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-05-15
 * Project        : wechat-mp-pom
 * File Name      : AutoSavePng.java
 */
@Component
public class AutoSavePng
{
    @Autowired
    private AssetService service;

    @Value("${pngPath}")
    private String pngPath;

    private static final Logger log = LoggerFactory.getLogger(AutoSavePng.class);

    public void excute()
    {
        log.warn("AutoSavePng begin ...........");
        try
        {
            Desktop desktop;
            //此方法仅适用于JdK1.6及以上版本
            if (Desktop.isDesktopSupported())
            {
                desktop = Desktop.getDesktop();
                try
                {
                    log.warn("open Url ::: {}", QqassetEnumFactory.PNG.getUrl());
                    //URI指定网页的地址
                    desktop.browse(new URI(QqassetEnumFactory.PNG.getUrl()));
                    Thread.sleep(10000);
                    log.warn("begin Upload ...");
                    MediaFile file = new MediaFile();
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
                    file.setMediaType(MediaType.IMAGE);
                    String path = pngPath + df.format(new Date()) + ".png";
                    File pngFile = new File(path);
                    if (pngFile.exists())
                    {
                        file.setFile(pngFile);
                        // 上传图片素材
                        MediaJsonRtn rtn = MediaUtil.uploadMedia(Constants.license, file);
                        System.out.println(rtn);
                        if (rtn != null && !StringUtils.trim(rtn.getMediaId()).equals(""))
                        {
                            saveRecord(df, path, rtn);
                            log.warn("save png ok");
                            // 上传图文消息
                            log.warn("upload newsMedia");
                            MediaJsonRtn rtn2 = MediaUtil.uploadNewsMedia(Constants.license, buildNewsMedia(rtn.getMediaId()));
                            saveRecord(df, path, rtn2);
                            log.warn("save news ok");
                            assert rtn2 != null;
                            MessageUtil.sendMassMessageByUsers(Constants.license, buildSendList(), rtn2.getMediaId());
                        }
                        log.warn("Upload finish , rtn is {}", rtn);
                    }
                    else
                    {
                        log.warn("file :: {} not exists", path);

                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            log.error("cannot open browse ", e);
        }
        log.warn("AutoSavePng end ...........");
    }

    public void testSendMsg()
    {
        log.warn("upload newsMedia");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        MediaJsonRtn rtn2 = MediaUtil.uploadNewsMedia(Constants.license, buildNewsMedia("QQtBLpRltyph_sVoDJ9BBISspyIx41iUiWVDCQHsc1p9bxpbZum-Fwct7Tr7HwXr"));
        saveRecord(df, "test", rtn2);
        log.warn("save news ok");
        assert rtn2 != null;
        MessageUtil.sendMassMessageByUsers(Constants.license, buildSendList(), rtn2.getMediaId());
    }

    private NewsMedia buildNewsMedia(String mediaId)
    {
        //TODO  定制
        List<NewsMediaDetail> articles = new ArrayList<NewsMediaDetail>();
        NewsMediaDetail detail = new NewsMediaDetail();
        detail.setTitle("尊敬的客户，您好，为您播报近期收益:");
        detail.setDigest("详细信息可以点击下方“旗下基金”查看");
        detail.setThumbMediaId(mediaId);
        detail.setContentSourceUrl("http://121.42.42.197/wechat/asset/fund.service?fId=1");
        articles.add(detail);
        return new NewsMedia(articles);
    }

    private List<String> buildSendList()
    {
        List<String> openids = new ArrayList<String>();
        openids.add("o6q3djvDpI-JfuNuozEFeDwnvFwk");
        openids.add("o6q3djp1CMh_RlvfWc--s6rJLgn8");
        return openids;
    }

    private void saveRecord(SimpleDateFormat df, String path, MediaJsonRtn rtn)
    {
        WechatMediasBean bean = new WechatMediasBean();
        bean.setDate(df.format(new Date()));
        bean.setPath(path);
        bean.setMedia_id(rtn.getMediaId());
        bean.setType(rtn.getType());
        bean.setCreated_at(String.valueOf(rtn.getCreatedAt()));
        service.save(bean);
    }

    public static void main(String[] args)
    {
        new AutoSavePng().excute();
    }
}
