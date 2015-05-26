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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private static final Logger log = LoggerFactory.getLogger(ViewAction.class);

    @Value("${pngPath}")
    private String pngPath;

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
    public String newsDetail(@RequestParam("ID") String ID, ModelMap model)
    {
        QqassetEnumFactory qq = EnumUtils.getEnum(QqassetEnumFactory.class, StringUtils.upperCase("detail"));
        Validate.notNull(qq, "don't-support-%s-type-message", "detail");
        PageDataProvider provider = qq.getDataProvider();
        model.put("news", provider.getPageData(ID));
        return "asset/newsdetail";
    }

    @RequestMapping(value = {"/fund.service"}, method = RequestMethod.GET)
    public String fund(@RequestParam(required = false, defaultValue = "1", value = "fId") String fId, ModelMap model)
    {
        List<AssetBean> list = assetService.loadAssetList(NumberUtils.toInt(fId, 1));
        List<FundBean> funds = assetService.loadFundList(NumberUtils.toInt(fId, 1));
        assetService.conver2ChartsData(list, model);
        model.put("funds", funds);
        return "asset/fund";
    }

    @RequestMapping(value = {"/showPng.service"}, method = RequestMethod.GET)
    public String showPng(@RequestParam(required = false, defaultValue = "1", value = "fId") String fId, ModelMap model)
    {
        List<AssetBean> list = assetService.loadAssetList(NumberUtils.toInt(fId, 1));
        List<FundBean> funds = assetService.loadFundList(NumberUtils.toInt(fId, 1));
        assetService.conver2ChartsData(list, model);
        model.put("funds", funds);
        return "asset/show";
    }

    @RequestMapping(value = {"/savePng.service"}, method = RequestMethod.POST)
    public void savePng(HttpServletRequest req)
    {
        String content = req.getParameter("pngContent");
        String data = URLDecoder.decode(content);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        try
        {
            System.out.println("save png begin ");
            log.warn("savePng begin....");
            String[] url = data.split(",");
            String u = url[1];
            // Base64解码
            byte[] b = new BASE64Decoder().decodeBuffer(u);
            // 生成图片
            OutputStream out = new FileOutputStream(new File(pngPath + df.format(new Date()) + ".png"));
            out.write(b);
            out.flush();
            out.close();
        }
        catch (Exception e)
        {
            log.warn("savePng error ::: {}", e.getMessage());
            e.printStackTrace();
        }
        System.out.println("savePng ok");
    }

}
