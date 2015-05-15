/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.sdk.qqasset.pagedata;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.rockey.wechat.mp.sdk.qqasset.QqassetEnumFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-27
 * Project        : wechat-mp-pom
 * File Name      : AboutDataProvider.java
 */
public class PNGDataProvider3 extends AbstractDataProvider
{
    private final static Logger LOGGER = LoggerFactory.getLogger(PNGDataProvider3.class);

    @Override
    public String getPageData(Object obj)
    {
        try
        {
            String html = buildRequest(QqassetEnumFactory.PNG, null);
            Document doc = Jsoup.parse(html);
            Elements input = doc.select("input#dd");
            String content = input.val();
            System.out.println("lalala");
            return "";
        }
        catch (Exception e)
        {
            LOGGER.error("cannot read cat data", e);
        }
        return "";
    }

    public static void main(String[] args)
    {
        PNGDataProvider3 pr = new PNGDataProvider3();
        String content = pr.getPageData(null);
        System.out.println(content);
    }
}
