/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.sdk.qqasset.pagedata;

import org.rockey.wechat.mp.sdk.qqasset.QqassetEnumFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.net.URI;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-27
 * Project        : wechat-mp-pom
 * File Name      : AboutDataProvider.java
 */
public class PNGDataProvider2 extends AbstractDataProvider
{
    private final static Logger LOGGER = LoggerFactory.getLogger(PNGDataProvider2.class);

    @Override
    public String getPageData(Object obj)
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
                    desktop.browse(new URI(QqassetEnumFactory.PNG.getName()));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("cannot open browse ", e);
        }
        return "";
    }

    public static void main(String[] args)
    {
        PNGDataProvider2 pr = new PNGDataProvider2();
        String content = pr.getPageData(null);
        System.out.println(content);
    }
}
