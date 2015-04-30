/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.service;

import org.apache.commons.collections.CollectionUtils;
import org.rockey.wechat.mp.web.dao.JdbcDao;
import org.rockey.wechat.mp.web.util.CommonUtil;
import org.rockey.wechat.mp.web.util.Constants;
import org.rockey.wechat.mp.web.vo.AssetBean;
import org.rockey.wechat.mp.web.vo.FundBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-29
 * Project        : wechat-mp-pom
 * File Name      : AssetService.java
 */
@Service
public class AssetService
{
    @Autowired
    @Qualifier("jdbcDao")
    private JdbcDao jdbcDao;

    public List<AssetBean> loadAssetList(int accountId)
    {
        List<AssetBean> assetlist = this.jdbcDao.getJdbcTemplate().query(Constants.QUERY_CHARTS,
                new Object[]{accountId},
                new RowMapper<AssetBean>()
                {
                    @Override
                    public AssetBean mapRow(ResultSet rs, int rowNum) throws SQLException
                    {
                        AssetBean bean = new AssetBean();
                        bean.setfId(rs.getInt("fId"));
                        bean.setFname(rs.getString("fname"));
                        bean.setQqassetname(rs.getString("qqassetname"));
                        bean.setAsset_balance(rs.getString("asset_balance"));
                        bean.setHs_300(rs.getString("hs_300"));
                        bean.setDates(rs.getString("dates"));
                        return bean;
                    }
                }
        );
        return !CollectionUtils.isEmpty(assetlist) ? assetlist : null;
    }

    public List<FundBean> loadFundList(int accountId)
    {
        List<FundBean> fundlist = this.jdbcDao.getJdbcTemplate().query(Constants.QUERY_LIST,
                new Object[]{accountId},
                new RowMapper<FundBean>()
                {
                    @Override
                    public FundBean mapRow(ResultSet rs, int rowNum) throws SQLException
                    {
                        DecimalFormat df = new DecimalFormat("#.####");
                        FundBean bean = new FundBean();
                        bean.setStock_code(rs.getString("stock_code"));
                        bean.setStock_name(rs.getString("stock_name"));
                        bean.setInit_dateString(CommonUtil.dateConvert(rs.getDate("init_date"), CommonUtil.Format_Default));
                        bean.setCurrent_amount(rs.getInt("current_amount"));
                        bean.setEnable_amount(rs.getInt("enable_amount"));
                        bean.setCost_price(df.format(rs.getDouble("cost_price")));
                        bean.setLast_price(df.format(rs.getDouble("last_price")));
                        bean.setMarket_value(df.format(rs.getDouble("market_value")));
                        bean.setIncome_balance(df.format(rs.getDouble("income_balance")));
                        return bean;
                    }
                }
        );
        return !CollectionUtils.isEmpty(fundlist) ? fundlist : null;
    }

    public void conver2ChartsData(List<AssetBean> list, ModelMap model)
    {
        if (!CollectionUtils.isEmpty(list))
        {
            List<String> assetBalance = new ArrayList<String>();
            List<String> hs300 = new ArrayList<String>();
            List<String> dates = new ArrayList<String>();
            String name = list.get(0).getQqassetname().concat(Constants.CHARTS_NAME);
            AssetBean base = list.get(0);
            for (AssetBean assetBean : list)
            {
                dates.add("'" + assetBean.getDates() + "'");
                Double ab = CommonUtil.doubleComputing(new Double(assetBean.getAsset_balance()), new Double(base.getAsset_balance()), "/", 4);
                Double hs = CommonUtil.doubleComputing(new Double(assetBean.getHs_300()), new Double(base.getHs_300()), "/", 4);
                assetBalance.add(ab.toString());
                hs300.add(hs.toString());
            }
            model.put("ab", assetBalance);
            model.put("hs", hs300);
            model.put("dates", dates);
            model.put("name", name);
        }
    }
}
