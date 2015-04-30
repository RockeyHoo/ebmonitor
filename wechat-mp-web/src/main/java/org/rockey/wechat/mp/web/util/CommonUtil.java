package org.rockey.wechat.mp.web.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 工具类
 */
public class CommonUtil
{
    private static final Log log = LogFactory.getLog(CommonUtil.class);

    public static final DecimalFormat df = new DecimalFormat(".####");

    /**
     * Double 类型运算
     *
     * @param d1    数值1
     * @param d2    数值2
     * @param type  运算类型："+" "-" "*" "/"
     * @param scale 保留小数位,除法禁止为0
     * @return Double
     */
    public static Double doubleComputing(Double d1, Double d2, String type, int scale)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        if (type.equals(Constants.BIGDECIMAL_TYPE_ADD))
        {
            return b1.add(b2).doubleValue();
        }
        else if (type.equals(Constants.BIGDECIMAL_TYPE_SUBTRACT))
        {
            return b1.subtract(b2).doubleValue();
        }
        else if (type.equals(Constants.BIGDECIMAL_TYPE_MULTIPLY))
        {
            return b1.multiply(b2).doubleValue();
        }
        else if (type.equals(Constants.BIGDECIMAL_TYPE_DIVIDE))
        {
            if (scale < 0)
            {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return b1.subtract(b2).doubleValue();
    }

    /**
     * Double 类型运算
     *
     * @param type  运算类型："+" "-" "*" "/"
     * @param scale 保留小数位,除法禁止为0
     * @return BigDecimal
     */
    public static BigDecimal doubleComputing(Object o1, Object o2, String type, int scale)
    {
        BigDecimal b1 = new BigDecimal(o1.toString());
        BigDecimal b2 = new BigDecimal(o2.toString());
        if (type.equals(Constants.BIGDECIMAL_TYPE_ADD))
        {
            return b1.add(b2);
        }
        else if (type.equals(Constants.BIGDECIMAL_TYPE_SUBTRACT))
        {
            return b1.subtract(b2);
        }
        else if (type.equals(Constants.BIGDECIMAL_TYPE_MULTIPLY))
        {
            return b1.multiply(b2);
        }
        else
        {
            if (scale < 0)
            {
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            }
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * 两个Double类型比较是否相等
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean doubleCompare(Double d1, Double d2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        if (b1.compareTo(b2) == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 判断某字符是否包含在当前字符串中
     * 根据起始、结束下标截取需比较的字符和传入的字符比较返回boolean
     *
     * @param str        比较字符串
     * @param c          比较字符
     * @param beginIndex 字符串开始下标
     * @param endIndex   字符串结束下标
     * @return
     */
    public static boolean charExistsString(String str, String c, int beginIndex, int endIndex)
    {
        c = c.toUpperCase();
        str = str.toUpperCase();
        String r = "";
        r = str.substring(beginIndex, endIndex);
        if (r.equals(c))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 格式化Double类型
     *
     * @param number  数字
     * @param pattern 格式，例如：##.00
     * @return
     */
    public static Double DecimalFormat(Double number, String pattern)
    {
        DecimalFormat df = new DecimalFormat(pattern);
        return Double.parseDouble(df.format(number));
    }

    /**
     * @param str   字符串
     * @param index 指定的索引位置
     * @return 是字母返回true，反之则返回false
     */
    public static boolean isCharacter(String str, int index)
    {
        char c = str.charAt(index);
        int i = (int) c;
        if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 日期运算
     *
     * @param dateStr   字符串格式的日期
     * @param formatStr 格式化类型,例如：yyyy-MM-dd、yyyy-MM-dd HH:mm:ss
     * @param num       运算数，正数为加，负数为减
     * @param year      年
     * @param month     月
     * @param day       日
     * @param hour      时
     * @param minute    分
     * @param second    秒
     * @return String
     * 例子：比如我要在获取的时间上增加一天，参数：2013-10-11,yyyy-MM-dd,1,false,false,true,false,false,false
     */
    public static String DateComput(String dateStr, String formatStr, int num, boolean year, boolean month, boolean day, boolean hour, boolean minute, boolean second)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Date dt = null;
        try
        {
            dt = sdf.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(dt);
        if (year)
        {//年
            currentDate.add(Calendar.YEAR, num);
        }
        if (month)
        {//月
            currentDate.add(Calendar.MARCH, num);
        }
        if (day)
        {//日
            currentDate.add(Calendar.DAY_OF_YEAR, num);
        }
        if (hour)
        {//时
            currentDate.add(Calendar.HOUR_OF_DAY, num);
        }
        if (minute)
        {//分
            currentDate.add(Calendar.MINUTE, num);
        }
        if (second)
        {//秒
            currentDate.add(Calendar.SECOND, num);
        }
        Date dtTemp = currentDate.getTime();
        String reStr = sdf.format(dtTemp);
        return reStr;
    }

    /**
     * 转换并格式化日期
     *
     * @param date      日期
     * @param formatStr 格式化类型,例如：yyyy-MM-dd、yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String dateConvert(Date date, String formatStr)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }

    public static final String Format_Default = "yyyy-MM-dd";

    public static void main(String[] args)
    {
        DecimalFormat df = new DecimalFormat("#.####");
        System.out.println(df.format(10.000001));
    }

}
