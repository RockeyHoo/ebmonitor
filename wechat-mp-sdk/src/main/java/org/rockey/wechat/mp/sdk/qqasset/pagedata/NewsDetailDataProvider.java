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

import java.io.IOException;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-27
 * Project        : wechat-mp-pom
 * File Name      : AboutDataProvider.java
 */
public class NewsDetailDataProvider extends AbstractDataProvider
{
    private final static Logger LOGGER = LoggerFactory.getLogger(NewsDetailDataProvider.class);

    @Override
    public String getPageData(Object obj)
    {
        try
        {
            Document doc = Jsoup.connect(String.format(QqassetEnumFactory.DETAIL.getUrl(),obj)).get();
            Elements div = doc.select(".4wechat");
            return div.outerHtml();
        }
        catch (Exception e)
        {
            LOGGER.error("cannot read cat data", e);
        }
        return "";
    }

    public static void main(String[] args) throws IOException
    {
       // NewsDetailDataProvider pr = new NewsDetailDataProvider();
        //pr.getPageData("39");

    }
}
