package org.rockey.wechat.mp.web.service;

import org.rockey.wechat.mp.sdk.util.platform.UserUtil;
import org.rockey.wechat.mp.sdk.vo.token.License;
import org.rockey.wechat.mp.sdk.vo.user.UsersJsonRtn;
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

    private static final License license2 = new License("test", "wx25c3d588ab1f527d", "de3a3cf8f6f632d8304b924ce2b83c89");

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

    @Test
    public void userList()
    {
        UsersJsonRtn users = UserUtil.getUsers(license2);
        List<String> list = users.getOpenIds().getOpenIds();
       /* for (String openId : list)
        {
            WechatUserBean bean = new WechatUserBean();
            UserInfoJsonRtn userInfo = UserUtil.getUserInfo(license2, openId);
            assetService.conver2WechatUser(userInfo, bean);
            boolean succ = assetService.save(bean);
            System.out.println(succ);
        }*/
    }

    @Test
    public void testList()
    {
        String msg = assetService.processMessage("o6q3djqDXQSq8tCvmgeer-l8I2FQ");
        System.out.println(msg);
    }
}