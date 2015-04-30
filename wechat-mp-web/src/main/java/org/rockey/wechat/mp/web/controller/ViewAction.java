/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.controller;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.rockey.wechat.mp.sdk.qqasset.QqassetEnumFactory;
import org.rockey.wechat.mp.sdk.qqasset.pagedata.PageDataProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-28
 * Project        : wechat-mp-pom
 * File Name      : AboutAction.java
 */
@Controller
@RequestMapping(value = {"/asset"})
public class ViewAction
{
    @RequestMapping(value = {"/about.service"}, method = RequestMethod.GET)
    public String about()
    {
        return "about/about";
    }

    @RequestMapping(value = {"/news.service"}, method = RequestMethod.GET)
    public String news(ModelMap model)
    {
        QqassetEnumFactory qq = EnumUtils.getEnum(QqassetEnumFactory.class, StringUtils.upperCase("news"));
        Validate.notNull(qq, "don't-support-%s-type-message", "about");
        PageDataProvider provider = qq.getDataProvider();
        model.put("news", provider.getPageData());
        return "about/news";
    }

}
