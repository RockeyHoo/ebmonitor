/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.vo;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-05-05
 * Project        : wechat-mp-pom
 * File Name      : WechatBean.java
 */
public class WechatMediasBean
{
    private String date;

    private String type;

    private String media_id;

    private String created_at;

    private String path;

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getMedia_id()
    {
        return media_id;
    }

    public void setMedia_id(String media_id)
    {
        this.media_id = media_id;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    public void setCreated_at(String created_at)
    {
        this.created_at = created_at;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Override
    public String toString()
    {
        return "WechatMediasBean{" +
                "date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", media_id='" + media_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
