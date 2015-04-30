/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.util;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-30
 * Project        : wechat-mp-pom
 * File Name      : Constants.java
 */
public class Constants
{
    public final static String BIGDECIMAL_TYPE_ADD = "+";

    public final static String BIGDECIMAL_TYPE_SUBTRACT = "-";

    public final static String BIGDECIMAL_TYPE_MULTIPLY = "*";

    public final static String BIGDECIMAL_TYPE_DIVIDE = "/";

    public static final String CHARTS_NAME = "与沪深300指数走势对照";

    public static final String QUERY_CHARTS = " select f.fId,f.fname,f.qqassetname, a.asset_balance,a.hs_300,CONVERT(varchar(100), a.Time, 111) dates from func_client_fund_all_qry a,fundaccount f where a.fundaccount_id=f.fId  and f.fId = ? order by a.Time asc";

    public static final String QUERY_LIST = "with de as ( select convert(varchar(10),(getdate()-DATEDIFF(DAY,MAX(init_date),getdate())),120) as ddate from dbo.func_secu_stock_qry (nolock)) select  * from dbo.func_secu_stock_qry (nolock) where init_date>=(select * from de) and init_date<convert( datetime,(select * from de))+1 and fundaccount_id =?";
}
