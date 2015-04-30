/*
 * Create Author  : shang.gao
 * Create Date     : 2013-4-8
 * Project            : cps-redirect-web
 * File Name        : Persistentable.java
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */
package org.rockey.wechat.mp.web.dao;

import java.util.Date;

/**
 * 
 * 功能描述:  持久化标识接口，用于为POJO对象提供数据库操作绑定 <p>
 * 
 *
 * @author : shang.gao <p>
 *
 * @version 1.0 2013-5-2
 *
 * @since cps-common 1.0
 */
public interface Persistentable
{
    public String getColumns();
    
    public String getTable();
    
    public String getValues();

    public Date getCreateTime();
    
    public void setCreateTime(Date createTime);
    
    public String getUniqueColumn();
    
    public String getUniqueKey();
}
