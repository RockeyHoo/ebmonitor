package org.rockey.wechat.mp.web.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：<p>
 * <p/>
 * <p/>
 * Author xiaopengli, 2014-04-21
 *
 * @since 1.0
 */
public class MySQLdbcDao implements JdbcDao,InitializingBean
{
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public boolean save(Persistentable po) throws DataAccessException
    {
        po.setCreateTime(Calendar.getInstance().getTime());
        String sql = SqlUtils.getInsertSql(po.getTable(), po.getColumns(), po.getValues());
        return this.jdbcTemplate.update(sql) == 1;
    }

    public List<Map<String, Object>> query(QueryInfo query)
    {
        Map<String, Object> conditions = query.getConditions();
        String sql = SqlUtils.getSelectSql(query.getTable(), query.getColumns(), conditions == null ? null : conditions.keySet().toArray(new String[conditions.size()]));
        return this.jdbcTemplate.queryForList(sql, conditions == null ? null : conditions.values().toArray());
    }

    public <T> T query(QueryInfo query, Class<T> type)
    {
        Map<String, Object> conditions = query.getConditions();
        String sql = SqlUtils.getSelectSql(query.getTable(), query.getColumns(), conditions == null ? null : conditions.keySet().toArray(new String[conditions.size()]));
        return this.jdbcTemplate.queryForObject(sql, type, conditions == null ? null : conditions.values().toArray(), type);
    }

    public long count(QueryInfo query)
    {
        Map<String, Object> conditions = query.getConditions();
        String sql = SqlUtils.getCountSql(query.getTable(), conditions == null ? null : conditions.keySet().toArray(new String[conditions.size()]));
        return this.jdbcTemplate.queryForLong(sql, conditions == null ? null : conditions.values().toArray());
    }

    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate()
    {
        return namedParameterJdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
