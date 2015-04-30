/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.service;

import org.apache.commons.collections.CollectionUtils;
import org.rockey.wechat.mp.web.dao.JdbcDao;
import org.rockey.wechat.mp.web.vo.AssetBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        List<AssetBean> assetlist = this.jdbcDao.getJdbcTemplate().query(
                " select f.fId,f.fname," +
                        "        f.qqassetname," +
                        "        a.asset_balance," +
                        "        a.hs_300," +
                        "        CONVERT(varchar(100), a.Time, 23) dates" +
                        "  from func_client_fund_all_qry a," +
                        "       fundaccount f" +
                        "  where a.fundaccount_id=f.fId " +
                        "        and f.fId = ?" +
                        "  order by f.fId,a.Time desc",
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
                        bean.setDates("dates");
                        return bean;
                    }
                }
        );
        return !CollectionUtils.isEmpty(assetlist) ? assetlist : null;
    }
}
