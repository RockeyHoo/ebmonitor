package org.rockey.wechat.mp.sdk.qqasset;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.rockey.wechat.mp.sdk.qqasset.pagedata.PageDataProvider;

public class QqassetEnumFactoryTest
{
    public static void main(String[] args)
    {
        QqassetEnumFactory qq = EnumUtils.getEnum(QqassetEnumFactory.class, StringUtils.upperCase("about"));
        Validate.notNull(qq, "don't-support-%s-type-message", "about");
        PageDataProvider provider = qq.getDataProvider();
        String[] content = (String[]) provider.getPageData();
        System.out.println("=========================");
        System.out.println(content);
        System.out.println("=========================");
    }

}