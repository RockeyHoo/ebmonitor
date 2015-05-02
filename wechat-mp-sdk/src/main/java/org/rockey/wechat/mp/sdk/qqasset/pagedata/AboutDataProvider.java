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
public class AboutDataProvider extends AbstractDataProvider
{
    private final static Logger LOGGER = LoggerFactory.getLogger(AboutDataProvider.class);

    @Override
    public String[] getPageData(Object obj)
    {
        try
        {
            String html = buildRequest(QqassetEnumFactory.ABOUT, null);
            Document doc = Jsoup.parse(html);
            Elements divs = doc.select("body > div:not(.opNav)");
            Elements dataScript = doc.select("body > script");
            return new String[]{divs.outerHtml(), dataScript.outerHtml()};
        }
        catch (Exception e)
        {
            LOGGER.error("cannot read cat data", e);
        }
        return new String[]{"", ""};
    }
}
