/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.sdk.qqasset.pagedata;

import org.jsoup.helper.StringUtil;
import org.rockey.wechat.mp.sdk.qqasset.QqassetEnumFactory;
import org.rockey.wechat.mp.sdk.util.HttpUtil;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-27
 * Project        : wechat-mp-pom
 * File Name      : AbstractDataProvider.java
 */
abstract class AbstractDataProvider implements PageDataProvider
{
    public String buildRequest(QqassetEnumFactory request, String param)
    {
        String content = null;
        try
        {
            if (StringUtil.isBlank(param))
            {
                return HttpUtil.getRequest(request, null);
            }
            else
            {
                return HttpUtil.getRequest(request, param, null);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
