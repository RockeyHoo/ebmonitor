/*
 * Create Author  : shang.gao
 * Create Date    : 2014-07-01
 * Project        : openplatform
 * File Name      : CatDataProvider.java
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.sdk.qqasset.pagedata;

public interface PageDataProvider<T>
{

    public T getPageData(Object obj);

}
