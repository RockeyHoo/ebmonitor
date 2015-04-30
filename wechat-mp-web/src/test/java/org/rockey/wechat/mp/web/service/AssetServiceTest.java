package org.rockey.wechat.mp.web.service;

import org.rockey.wechat.mp.web.vo.AssetBean;
import org.rockey.wechat.mp.web.vo.FundBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

@ContextConfiguration(locations = {"classpath:/config/applicationContext-core.xml"})
public class AssetServiceTest extends AbstractTestNGSpringContextTests
{

    @Autowired
    private AssetService assetService;

    @Test
    public void testLoadAssetList() throws Exception
    {
        List<AssetBean> list = assetService.loadAssetList(1);
        System.out.println("====================");
        System.out.println(list);
        System.out.println("====================");
    }

    @Test
    public void testLoadFundList() throws Exception
    {
        List<FundBean> list = assetService.loadFundList(1);
        System.out.println("====================");
        System.out.println(list);
        System.out.println("====================");
    }
}