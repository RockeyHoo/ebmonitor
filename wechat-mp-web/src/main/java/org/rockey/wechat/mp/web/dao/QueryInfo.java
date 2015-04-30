package org.rockey.wechat.mp.web.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：<p>
 *
 *
 * Author xiaopengli, 2014-04-21
 * @since 1.0
 *
 */
public class QueryInfo implements Serializable
{
    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 3412535589476808809L;

    private String table;
    
    private List<String> columns;
    
    private LinkedHashMap<String, Object> conditions;

    public void addColumn(String column)
    {
        if(this.columns == null)
        {
            this.columns = new ArrayList<String>();
        }
        this.columns.add(column);
    }

    public void addCondition(String column, Object value)
    {
        if(this.conditions == null)
        {
            this.conditions = new LinkedHashMap<String, Object>();
        }
        this.conditions.put(column, value);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QueryInfo other = (QueryInfo) obj;
        if (columns == null)
        {
            if (other.columns != null)
                return false;
        }
        else if (!columns.equals(other.columns))
            return false;
        if (conditions == null)
        {
            if (other.conditions != null)
                return false;
        }
        else if (!conditions.equals(other.conditions))
            return false;
        if (table == null)
        {
            if (other.table != null)
                return false;
        }
        else if (!table.equals(other.table))
            return false;
        return true;
    }

    public List<String> getColumns()
    {
        return columns;
    }

    public Map<String, Object> getConditions()
    {
        return conditions;
    }

    public String getTable()
    {
        return table;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((columns == null) ? 0 : columns.hashCode());
        result = prime * result + ((conditions == null) ? 0 : conditions.hashCode());
        result = prime * result + ((table == null) ? 0 : table.hashCode());
        return result;
    }

    public void setColumns(List<String> columns)
    {
        this.columns = columns;
    }

    public void setConditions(LinkedHashMap<String, Object> conditions)
    {
        this.conditions = conditions;
    }

    public void setTable(String table)
    {
        this.table = table;
    }

    @Override
    public String toString()
    {
        return "QueryInfo [table=" + table + ", columns=" + columns + ", conditions=" + conditions
               + "]";
    }
}
