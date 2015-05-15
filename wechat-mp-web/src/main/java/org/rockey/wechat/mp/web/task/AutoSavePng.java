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
import org.rockey.wechat.mp.sdk.vo.media.MediaFile;
import org.rockey.wechat.mp.sdk.vo.media.MediaJsonRtn;
import org.rockey.wechat.mp.sdk.vo.media.MediaType;
import org.rockey.wechat.mp.web.service.AssetService;
import org.rockey.wechat.mp.web.util.Constants;
import org.rockey.wechat.mp.web.vo.WechatMediasBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                    String path = "C:/apache/wechatPng/" + df.format(new Date()) + ".png";
                    File pngFile = new File(path);
                    if (pngFile.exists())
                    {
                        file.setFile(pngFile);
                        MediaJsonRtn rtn = MediaUtil.uploadMedia(Constants.license, file);
                        System.out.println(rtn);
                        if (rtn != null && !StringUtils.trim(rtn.getMediaId()).equals(""))
                        {
                            WechatMediasBean bean = new WechatMediasBean();
                            bean.setDate(df.format(new Date()));
                            bean.setPath(path);
                            bean.setMedia_id(rtn.getMediaId());
                            bean.setType(rtn.getType());
                            bean.setCreated_at(String.valueOf(rtn.getCreatedAt()));
                            service.save(bean);
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

    public static void main(String[] args)
    {
        new AutoSavePng().excute();
    }
}
