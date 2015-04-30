/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.vo;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-29
 * Project        : wechat-mp-pom
 * File Name      : AssetBean.java
 */
public class AssetBean
{
      private Integer fId;

      private String fname;

      private String qqassetname;

      private String asset_balance;

      private String hs_300;

      private String dates;

      public Integer getfId()
      {
            return fId;
      }

      public void setfId(Integer fId)
      {
            this.fId = fId;
      }

      public String getFname()
      {
            return fname;
      }

      public void setFname(String fname)
      {
            this.fname = fname;
      }

      public String getQqassetname()
      {
            return qqassetname;
      }

      public void setQqassetname(String qqassetname)
      {
            this.qqassetname = qqassetname;
      }

      public String getAsset_balance()
      {
            return asset_balance;
      }

      public void setAsset_balance(String asset_balance)
      {
            this.asset_balance = asset_balance;
      }

      public String getHs_300()
      {
            return hs_300;
      }

      public void setHs_300(String hs_300)
      {
            this.hs_300 = hs_300;
      }

      public String getDates()
      {
            return dates;
      }

      public void setDates(String dates)
      {
            this.dates = dates;
      }

      @Override
      public String toString()
      {
            return "AssetBean{" +
                    "fId=" + fId +
                    ", fname='" + fname + '\'' +
                    ", qqassetname='" + qqassetname + '\'' +
                    ", asset_balance='" + asset_balance + '\'' +
                    ", hs_300='" + hs_300 + '\'' +
                    ", dates='" + dates + '\'' +
                    '}';
      }
}
