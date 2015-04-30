package org.rockey.wechat.mp.web.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public interface JdbcDao
{
    public void setDataSource(DataSource dataSource);

    public boolean save(Persistentable po) throws DataAccessException;

    public List<Map<String, Object>> query(QueryInfo query);

    public <T> T query(QueryInfo query, Class<T> type);

    public long count(QueryInfo query);

    public JdbcTemplate getJdbcTemplate();

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();

}
