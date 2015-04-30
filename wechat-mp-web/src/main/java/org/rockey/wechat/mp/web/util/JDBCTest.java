/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanHai Information Co., Ltd.
 * All rights reserved.
 *
 */

package org.rockey.wechat.mp.web.util;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Create Author  : shuang.he
 * Create Date    : 2015-04-29
 * Project        : wechat-mp-pom
 * File Name      : JDBCTest.java
 */
public class JDBCTest
{
    public static void main(String arg[])
    {
        try
        {
            Connection con = null;
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://121.42.42.197:1433/test", "sa", "WWWqqassetcom2014");
            System.out.print("yes");
        }
        catch (Exception e)
        {
            System.out.print("sqlServer ERROR:" + e.getMessage());
        }

    }
}
