/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.vo;

import net.sourceforge.jtds.jdbc.DateTime;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-30
 * Project        : wechat-mp-pom
 * File Name      : FundBean.java
 */
public class FundBean
{
    public String stock_code;

    public String stock_name;

    public int current_amount;

    public int enable_amount;

    public String last_price;

    public String cost_price;

    public String income_balance;

    public String market_value;

    public String fund_account;

    public int fundaccount_id;

    public DateTime init_date;

    public String init_dateString;

    public String getStock_code()
    {
        return stock_code;
    }

    public void setStock_code(String stock_code)
    {
        this.stock_code = stock_code;
    }

    public String getStock_name()
    {
        return stock_name;
    }

    public void setStock_name(String stock_name)
    {
        this.stock_name = stock_name;
    }

    public int getCurrent_amount()
    {
        return current_amount;
    }

    public void setCurrent_amount(int current_amount)
    {
        this.current_amount = current_amount;
    }

    public int getEnable_amount()
    {
        return enable_amount;
    }

    public void setEnable_amount(int enable_amount)
    {
        this.enable_amount = enable_amount;
    }

    public String getLast_price()
    {
        return last_price;
    }

    public void setLast_price(String last_price)
    {
        this.last_price = last_price;
    }

    public String getCost_price()
    {
        return cost_price;
    }

    public void setCost_price(String cost_price)
    {
        this.cost_price = cost_price;
    }

    public String getIncome_balance()
    {
        return income_balance;
    }

    public void setIncome_balance(String income_balance)
    {
        this.income_balance = income_balance;
    }

    public String getMarket_value()
    {
        return market_value;
    }

    public void setMarket_value(String market_value)
    {
        this.market_value = market_value;
    }

    public String getFund_account()
    {
        return fund_account;
    }

    public void setFund_account(String fund_account)
    {
        this.fund_account = fund_account;
    }

    public int getFundaccount_id()
    {
        return fundaccount_id;
    }

    public void setFundaccount_id(int fundaccount_id)
    {
        this.fundaccount_id = fundaccount_id;
    }

    public DateTime getInit_date()
    {
        return init_date;
    }

    public void setInit_date(DateTime init_date)
    {
        this.init_date = init_date;
    }

    public String getInit_dateString()
    {
        return init_dateString;
    }

    public void setInit_dateString(String init_dateString)
    {
        this.init_dateString = init_dateString;
    }
}
