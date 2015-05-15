package org.rockey.wechat.mp.sdk.qqasset;

import org.apache.commons.lang3.StringUtils;
import org.rockey.wechat.mp.sdk.qqasset.pagedata.*;

public enum QqassetEnumFactory
{
    ABOUT("http://www.qqasset.com/about.aspx", new AboutDataProvider()),
    NEWS("http://www.qqasset.com/news.aspx?type=12&nav=3", new NewsDataProvider()),
    DETAIL("http://www.qqasset.com/newsdetails.aspx?ID=%s&nav=3", new NewsDetailDataProvider()),
    PNG("http://121.42.42.197/wechat/asset/showPng.service", new PNGDataProvider2());

    private String url;

    private PageDataProvider dataProvider;

    private QqassetEnumFactory(String url, PageDataProvider dataProvider)
    {
        this.url = url;
        this.dataProvider = dataProvider;
    }

    public String getName()
    {
        return StringUtils.lowerCase(StringUtils.replace(name(), "_", " "));
    }

    public String getUrl()
    {
        return url;
    }

    public PageDataProvider getDataProvider()
    {
        return dataProvider;
    }
}