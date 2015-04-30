package org.rockey.wechat.mp.sdk.qqasset;

import org.apache.commons.lang3.StringUtils;
import org.rockey.wechat.mp.sdk.qqasset.pagedata.AboutDataProvider;
import org.rockey.wechat.mp.sdk.qqasset.pagedata.NewsDataProvider;
import org.rockey.wechat.mp.sdk.qqasset.pagedata.PageDataProvider;

public enum QqassetEnumFactory
{
    ABOUT("http://www.qqasset.com/about.aspx", new AboutDataProvider()),
    NEWS("http://www.qqasset.com/news.aspx", new NewsDataProvider());

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