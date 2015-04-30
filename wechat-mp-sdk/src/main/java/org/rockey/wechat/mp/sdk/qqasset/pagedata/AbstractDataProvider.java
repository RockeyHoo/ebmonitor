/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.sdk.qqasset.pagedata;

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
    public String buildRequest(QqassetEnumFactory request)
    {
        String content = null;
        try
        {
            return HttpUtil.getRequest(request, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
