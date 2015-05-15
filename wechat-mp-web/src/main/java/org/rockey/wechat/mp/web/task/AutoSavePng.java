/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.task;

import org.rockey.wechat.mp.sdk.qqasset.QqassetEnumFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-05-15
 * Project        : wechat-mp-pom
 * File Name      : AutoSavePng.java
 */
@Component
public class AutoSavePng
{
    private static final Logger log = LoggerFactory.getLogger(AutoSavePng.class);

    public void excute()
    {
        try
        {
            Desktop desktop;
            //此方法仅适用于JdK1.6及以上版本
            if (Desktop.isDesktopSupported())
            {
                desktop = Desktop.getDesktop();
                try
                {
                    //URI指定网页的地址
                    desktop.browse(new URI(QqassetEnumFactory.PNG.getUrl()));
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
    }

    public static void main(String[] args)
    {
        new AutoSavePng().excute();
    }
}
