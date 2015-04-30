package org.rockey.wechat.mp.web.dao;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：<p>
 * <p/>
 * <p/>
 * Author xiaopengli, 2014-04-21
 *
 * @since 1.0
 */
public final class SqlUtils
{

    public final static String STR_BLANK = " ";

    public final static char SINGLE_QUOTE = '\'';

    public final static String NULL_QUOTE_STR = "''";

    public final static String SLASH_STR = "/";

    private static final char CHAR_SPACE = ' ';

    private static final char CHAR_PLUS = '+';

    public final static char ZERO = '0';

    public final static char DOUBLE_QUOTE = '\"';

    public final static char COLON = ':';

    public final static char COMMA = ',';

    public final static char EQUAL = '=';

    public final static String QUOTE = "'";

    public final static char PLACEHOLDER_ARG = '?';

    public final static String SQL_AND = " AND ";

    public final static String SQL_COUNT = "COUNT(*)";

    public final static String SQL_ALL_COLUMN = "*";

    public final static String SQL_DEFAULT_CONDITION = "1=1";

    private final static String PLACEHOLDER_TABLE = "#TABLE#";

    private final static String PLACEHOLDER_COLUMNS = "#COLUMNS#";

    private final static String PLACEHOLDER_VALUES = "#VALUES#";

    private final static String PLACEHOLDER_CONDITIONS = "#VALUES#";

    private final static String SQL_INSERT = "INSERT INTO " + PLACEHOLDER_TABLE + " (" + PLACEHOLDER_COLUMNS + ") VALUES (" + PLACEHOLDER_VALUES + ")";

    private final static String SQL_SELECT = "SELECT " + PLACEHOLDER_COLUMNS + " FROM " + PLACEHOLDER_TABLE + " WHERE " + PLACEHOLDER_CONDITIONS;

    public static void appendStringValue(Object value, Class<?> type, StringBuilder buffer)
    {
        if (type == null)
        {
            type = value.getClass();
        }
        if (type == String.class)
        {
            if (value == null)
            {
                buffer.append(NULL_QUOTE_STR);
            }
            else
            {
                buffer.append(SINGLE_QUOTE).append(value).append(SINGLE_QUOTE);
            }
        }
        else if (Number.class.isAssignableFrom(type))
        {
            if (value == null)
            {
                buffer.append(ZERO);
            }
            else
            {
                buffer.append(value);
            }
        }
        else if (Date.class.isAssignableFrom(type))
        {
            if (value == null)
            {
                buffer.append(SINGLE_QUOTE).append(getCurrentDateTime()).append(SINGLE_QUOTE);
            }
            else
            {
                buffer.append(SINGLE_QUOTE).append(getFormatedDate("yyyy-MM-dd HH:mm:ss", (Date) value)).append(SINGLE_QUOTE);
            }

        }
    }

    public static String getInsertSql(String table, String columns, String values)
    {
        String tmp = StringUtils.replace(SQL_INSERT, PLACEHOLDER_TABLE, table);
        tmp = StringUtils.replace(tmp, PLACEHOLDER_COLUMNS, columns);
        return StringUtils.replace(tmp, PLACEHOLDER_VALUES, values);
    }

    public static String getCountSql(String table, String... conditionNames)
    {
        String conditionSql = getConditionSQL(conditionNames);
        return SQL_SELECT.replaceAll(PLACEHOLDER_TABLE, table).replaceAll(PLACEHOLDER_COLUMNS, SQL_COUNT).replaceAll(PLACEHOLDER_CONDITIONS, conditionSql);

    }

    public static String getSelectSql(String table, List<String> columns, String... conditionNames)
    {
        String columnSql = getColumns(columns);
        String conditionSql = getConditionSQL(conditionNames);
        return SQL_SELECT.replaceAll(PLACEHOLDER_TABLE, table).replaceAll(PLACEHOLDER_COLUMNS, columnSql).replaceAll(PLACEHOLDER_CONDITIONS, conditionSql);
    }

    private static String getConditionSQL(String... conditionNames)
    {
        String conditionSql = null;
        if (conditionNames != null)
        {
            StringBuilder buffer = new StringBuilder();
            int count = 0;
            for (String conditionName : conditionNames)
            {
                if (count++ > 0)
                {
                    buffer.append(SQL_AND);
                }
                buffer.append(conditionName).append(EQUAL).append(PLACEHOLDER_ARG);

            }
            conditionSql = buffer.toString();
        }
        else
        {
            conditionSql = SQL_DEFAULT_CONDITION;
        }
        return conditionSql;
    }

    /**
     * 功能描述：<p>
     * <p/>
     * 前置条件：<p>
     * <p/>
     * 方法影响： <p>
     * <p/>
     * Author shang.gao, 2013-5-2
     *
     * @param columns
     * @return
     * @since cps-common 2.0
     */
    private static String getColumns(List<String> columns)
    {
        String columnSql = null;
        if (columns != null)
        {
            int count = 0;
            StringBuilder buffer = new StringBuilder();
            for (String column : columns)
            {
                buffer.append(column);
                if (count++ < columns.size() - 1)
                {
                    buffer.append(COMMA);
                }
            }
            columnSql = buffer.toString();
        }
        else
        {
            columnSql = SQL_ALL_COLUMN;
        }
        return columnSql;
    }
    public static String getCurrentDateTime() {
        return getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDateTime(String dateFormat) {
        return (new SimpleDateFormat(dateFormat)).format(Calendar.getInstance().getTime());
    }


    public static String getFormatedDate(String dateFormat, Date date) {
        return date == null?null:(new SimpleDateFormat(dateFormat)).format(date);
    }
}
