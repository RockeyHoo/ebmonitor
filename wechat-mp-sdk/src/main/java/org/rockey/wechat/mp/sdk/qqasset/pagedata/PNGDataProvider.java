/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.sdk.qqasset.pagedata;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-27
 * Project        : wechat-mp-pom
 * File Name      : AboutDataProvider.java
 */
public class PNGDataProvider extends AbstractDataProvider
{
    private final static Logger LOGGER = LoggerFactory.getLogger(PNGDataProvider.class);

    @Override
    public String getPageData(Object obj)
    {
        try
        {
            WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
            webClient.setCssErrorHandler(new SilentCssErrorHandler());
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setRedirectEnabled(false);
            webClient.getOptions().setAppletEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setPopupBlockerEnabled(false);
            webClient.getOptions().setTimeout(10000);
            HtmlPage page = webClient.getPage("http://www.baidu.com");
            System.out.println(page.asXml());
            webClient.closeAllWindows();
        }
        catch (Exception e)
        {
            LOGGER.error("cannot read png data", e);
        }
        return "";
    }

    public static void main(String[] args)
    {
        PNGDataProvider pr = new PNGDataProvider();
        String content = pr.getPageData(null);
        System.out.println(content);
    }
}
