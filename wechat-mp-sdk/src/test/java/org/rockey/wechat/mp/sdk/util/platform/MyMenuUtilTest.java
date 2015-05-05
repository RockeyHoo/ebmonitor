package org.rockey.wechat.mp.sdk.util.platform;

import org.rockey.wechat.mp.sdk.vo.menu.Menu;
import org.rockey.wechat.mp.sdk.vo.menu.MenuInfo;
import org.rockey.wechat.mp.sdk.vo.menu.MenuType;
import org.rockey.wechat.mp.sdk.vo.token.License;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RockeyHoo
 */
public class MyMenuUtilTest
{
    public static final License license = new License("test", "wx25c3d588ab1f527d", "de3a3cf8f6f632d8304b924ce2b83c89");

    public static void main(String[] args) throws URISyntaxException
    {
        List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
        menuInfos.add(MenuType.VIEW.buildSingleMenuInfo("走进泉桥", "http://121.42.42.197/wechat/asset/about.service"));
        menuInfos.add(MenuType.CLICK.buildSingleMenuInfo("旗下基金", "fund_click"));
        menuInfos.add(MenuType.VIEW.buildSingleMenuInfo("泉桥视界", "http://121.42.42.197/wechat/asset/news.service"));
        Menu menu = new Menu(menuInfos);
        System.out.println(MenuUtil.createMenu(license, menu));
        System.out.println(MenuUtil.getMenu(license));
        // System.out.println(MenuUtil.deleteMenu(license));
    }
}
