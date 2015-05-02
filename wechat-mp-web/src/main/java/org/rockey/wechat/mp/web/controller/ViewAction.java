/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.controller;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.rockey.wechat.mp.sdk.qqasset.QqassetEnumFactory;
import org.rockey.wechat.mp.sdk.qqasset.pagedata.PageDataProvider;
import org.rockey.wechat.mp.web.service.AssetService;
import org.rockey.wechat.mp.web.vo.AssetBean;
import org.rockey.wechat.mp.web.vo.FundBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    @Autowired
    private AssetService assetService;

    @RequestMapping(value = {"/about.service"}, method = RequestMethod.GET)
    public String about()
    {
        return "asset/about";
    }

    @RequestMapping(value = {"/news.service"}, method = RequestMethod.GET)
    public String news(ModelMap model)
    {
        QqassetEnumFactory qq = EnumUtils.getEnum(QqassetEnumFactory.class, StringUtils.upperCase("news"));
        Validate.notNull(qq, "don't-support-%s-type-message", "news");
        PageDataProvider provider = qq.getDataProvider();
        model.put("news", provider.getPageData(null));
        return "asset/news";
    }

    @RequestMapping(value = {"/newsdetails.service"}, method = RequestMethod.GET)
    public String newsDetail(@RequestParam("ID") String ID,ModelMap model)
    {
        QqassetEnumFactory qq = EnumUtils.getEnum(QqassetEnumFactory.class, StringUtils.upperCase("detail"));
        Validate.notNull(qq, "don't-support-%s-type-message", "detail");
        PageDataProvider provider = qq.getDataProvider();
        model.put("news", provider.getPageData(ID));
        return "asset/newsdetail";
    }

    @RequestMapping(value = {"/fund.service"}, method = RequestMethod.GET)
    public String fund(@RequestParam("fId") String fId, ModelMap model)
    {
        List<AssetBean> list = assetService.loadAssetList(NumberUtils.toInt(fId, 1));
        List<FundBean> funds = assetService.loadFundList(NumberUtils.toInt(fId, 1));
        assetService.conver2ChartsData(list, model);
        model.put("funds", funds);
        return "asset/fund";
    }

}
